package uni.exercise.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
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
        List<Object> userInput = Arrays.asList(parameters);

        for (Object input : userInput) {
            index = userInput.indexOf(input) + 1;
            if (input instanceof String) {
                preparedStatement.setString(index, (String) input);
            }
        }
        return (isWrite)? preparedStatement.executeUpdate() : preparedStatement.executeQuery();
    }

    private static HashMap<String, String> retrieveData(ResultSet fromDB,
                                                        List<Object> dataToRetrieve) throws SQLException {


        HashMap<String, String> retrievedData = new HashMap<>();
        if (!fromDB.next()) return new HashMap<>();

        for (Object retrieve : dataToRetrieve) {
            retrievedData.put((String) retrieve, fromDB.getString((String) retrieve));
        }

        return retrievedData;
    }

    public static HashMap<String, String> getFromDatabase(String selector,
                                                          String query,
                                                          Connection conn,
                                                          String table,
                                                          Object... retrieves) throws SQLException {

        if (conn == null) throw new SQLException("Failed to establish connection");
        ResultSet resultsFromDB;
        resultsFromDB = (ResultSet) QueryManager.queryExecutor(MessageFormat.format(query, table), conn,false, selector);
        assert resultsFromDB != null;
        return QueryManager.retrieveData(resultsFromDB, Arrays.asList(retrieves));
    }

    public static void saveToDatabase(String query,
                                      Connection conn,
                                      String table,
                                      Object... fields) throws SQLException {
        if (conn == null) throw new SQLException("Failed to establish connection");
        QueryManager.queryExecutor(MessageFormat.format(query, table), conn, true, fields);
    }

}
