package uni.exercise.db;

public enum Queries {

    ADD_USER("INSERT INTO {0} (username, password, salt, address, email) VALUES(?, ?,?, ?, ?)"),
    REMOVE_USER("DELETE FROM {0} WHERE username = ?"),
    USER_STAR_REST("INSERT INTO {0} (user_id, restaurant_id) VALUES(?, ?)"),
    ADD_REST("INSERT INTO {0} (restaurant_owner, restaurant_name, restaurant_address, restaurant_phone, restaurant_pic, restaurant_filters) VALUES(?, ?, ?, ?, ?, ?) "),
    REMOVE_REST("DELETE FROM {0} WHERE restaurant_name = ?"),
    SEARCH_REST("SELECT * FROM {0} WHERE restaurant_name = ?"),
    SEARCH_REST_FILTERS("SELECT * FROM {0} WHERE restaurant_filters = ?"),
    GET_STARS("SELECT COUNT(*) AS stars FROM {0} WHERE restaurant_id = ?"),
    GET_RESTS("SELECT * FROM {0} natural join (SELECT restaurant_name, COUNT(restaurant_address) FROM (SELECT * FROM stared natural join restaurant) as test GROUP BY restaurant_name order by count DESC) as result;"),
    GET_OWNER("SELECT user_id FROM {0} WHERE rest_user.username = ?"),
    GET_OWNER_BY_OWNER_ID("SELECT username FROM {0} WHERE rest_user.user_id = ?"),
    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?"),
    CHECK_IF_USER_IS_ADMIN("SELECT * FROM {0}, rest_user WHERE username = ? AND rest_user.user_id = app_admin.user_id"),
    UPDATE_DETAILS("UPDATE {0} SET address = ?, email = ? WHERE username = ?"),
    CHANGE_PASSWORD("UPDATE {0} SET password = ?, salt = ? WHERE email = ?");


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
