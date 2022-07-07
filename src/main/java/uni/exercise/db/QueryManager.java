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
                                        String... parameters) throws SQLException {
        int index;

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        List<String> userInput = Arrays.asList(parameters);

        for (String input : userInput) {
            index = userInput.indexOf(input) + 1;
            preparedStatement.setString(index, (String) input);
        }
        return (isWrite)? preparedStatement.executeUpdate() : preparedStatement.executeQuery();
    }

    private static HashMap<String, String> retrieveData(ResultSet fromDB,
                                                        List<String> dataToRetrieve) throws SQLException {


        HashMap<String, String> retrievedData = new HashMap<>();
        if (!fromDB.next()) return new HashMap<>();

        for (String retrieve : dataToRetrieve) {
            retrievedData.put(retrieve, fromDB.getString(retrieve));
        }

        return retrievedData;
    }

    public HashMap<String, String> getFromDatabase(String selector,
                                                   String query,
                                                   Connection conn,
                                                   String table,
                                                   String... retrieves) throws SQLException {

        if (conn == null) throw new SQLException("Failed to establish connection");
        ResultSet resultsFromDB;
        resultsFromDB = (ResultSet) QueryManager.queryExecutor(MessageFormat.format(query, table), conn,false, selector);
        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

    // gets more than one column
    public ArrayList<HashMap<String, String>> getMultipleColDB(String query,
                                                               Connection conn,
                                                               String table,
                                                               String... params) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");

        ArrayList<HashMap<String, String>> records = new ArrayList<>();
        ArrayList<String> parameters = (ArrayList<String>) Arrays.asList(params);
        HashMap<String, String> tmp;
        ResultSet columns = (ResultSet) QueryManager.queryExecutor(MessageFormat.format(query, table), conn, false, params);

        int curr_col = 0;
        // while there are results make the appropriate number of hashmaps and store each record..
        while (columns.next()) {
            tmp = new HashMap<>();
            for (String p : parameters
                 ) {
                tmp.put(p, columns.getString(p));
            }
            records.add(tmp);
        }
        return records;
    }

    public void saveToDatabase(String query,
                               Connection conn,
                               String table,
                               String... fields) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");
        QueryManager.queryExecutor(MessageFormat.format(query, table), conn, true, fields);
    }
}
