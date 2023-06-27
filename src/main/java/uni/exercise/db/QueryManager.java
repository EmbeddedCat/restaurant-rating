package uni.exercise.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QueryManager {
    private static Object queryExecutor(String query,
                                        Connection conn,
                                        boolean isWrite,
                                        Object... parameters) throws SQLException {
        int index;

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        if (parameters != null) {
            List<Object> userInput = Arrays.asList(parameters);

            for (Object input : userInput) {
                index = userInput.indexOf(input) + 1;
                preparedStatement.setObject(index, input);                
            }
        }
        return (isWrite)? preparedStatement.executeUpdate() : preparedStatement.executeQuery();
    }

    private static HashMap<String, Object> retrieveData(ResultSet fromDB,
                                                        List<String> dataToRetrieve) throws SQLException {


        HashMap<String, Object> retrievedData = new HashMap<>();
        if (!fromDB.next()) return new HashMap<>();

        for (String retrieve : dataToRetrieve) {
            retrievedData.put(retrieve, fromDB.getObject(retrieve));
        }

        return retrievedData;
    }

    public HashMap<String, Object> getFromDatabase(Object selector,
                                                   String query,
                                                   Connection conn,
                                                   String table,
                                                   String... retrieves) throws SQLException {

        if (conn == null) throw new SQLException("Failed to establish connection");
        ResultSet resultsFromDB;
        resultsFromDB = (ResultSet) QueryManager.queryExecutor(MessageFormat.format(query, table), conn, false, selector);

        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

    // gets more than one column
    public ArrayList<HashMap<String, Object>> getMultipleColDB(String query,
                                                               Connection conn,
                                                               String table,
                                                               String... params) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");

        ArrayList<HashMap<String, Object>> records = new ArrayList<>();
        List<String> parameters = Arrays.asList(params);
        HashMap<String, Object> tmp;
        ResultSet columns = (ResultSet) QueryManager.queryExecutor(MessageFormat.format(query, table), conn, false, null);

        int curr_col = 0;
        // while there are results make the appropriate number of hashmaps and store each record..
        while (columns.next()) {
            tmp = new HashMap<>();

            for (String parameter :
                    parameters) {
                tmp.put(parameter, columns.getObject(parameter));
            }

            records.add(tmp);
        }
        return records;
    }

    public void removeFromDB(String selector,
                             String query,
                             Connection conn,
                             String table) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");

        QueryManager.queryExecutor(MessageFormat.format(query, table), conn, true, selector);
    }

    public void saveToDatabase(String query,
                               Connection conn,
                               String table,
                               Object... fields) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");
        QueryManager.queryExecutor(MessageFormat.format(query, table), conn, true, fields);
    }
}
