package uni.exercise.db;

public enum Queries {

    ADD_USER("INSERT INTO {0} (username, password, salt, address, email) VALUES(?, ?,?, ?, ?)"),
    REMOVE_USER("DELETE FROM {0} WHERE username = ?"),
    USER_STAR_REST("INSERT INTO {0} (username, restaurant_address) VALUES(?, ?)"),
    ADD_REST("INSERT INTO {0} (restaurant_owner, restaurant_name, restaurant_address, restaurant_phone, restaurant_pic) VALUES(?, ?, ?, ?, ?) "),
    REMOVE_REST("DELETE FROM {0} WHERE restaurant_address = ?"),
    SEARCH_REST("SELECT * FROM {0} WHERE restaurant_name = ?"),
    GET_STARS("SELECT COUNT(*) AS stars FROM {0} WHERE restaurant_address = ?"),
    GET_RESTS("SELECT * FROM {0} natural join (SELECT restaurant_name, COUNT(restaurant_address) FROM (SELECT * FROM stared natural join restaurant) as test GROUP BY restaurant_name order by count DESC) as result;"),
    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?");

    // Add a query to retrieve restaurant based on filter data.
    // Add a query to update user details if the user want to change them
    // Add a query to allow the users to change their passwords.

    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

}
