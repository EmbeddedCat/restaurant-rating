package uni.exercise.db;

public enum Queries {

    ADD_USER("INSERT INTO {0} (username, password, address, email) VALUES(?, ?, ?, ?)"),
    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?");


    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
