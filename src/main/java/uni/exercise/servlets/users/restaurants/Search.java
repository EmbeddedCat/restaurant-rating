package uni.exercise.servlets.users.restaurants;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet("/Search")
public class Search extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        // Make a new query manager.
        QueryManager queryManager = new QueryManager();
        // Establish new connection to the database.
        DBConnection dbConnection = new DBConnection();
        // get restaurant name.
        String name           = (String) request.getParameter("rest_name");
        String veganFilter    = (String) request.getParameter("vegan");
        String streetFilter   = (String) request.getParameter("street");
        String fastFoodFilter = (String) request.getParameter("fastfood");

        Boolean useFilters = false;
        StringBuilder filters = new StringBuilder();
        if (veganFilter != null || streetFilter != null || fastFoodFilter != null) {
            useFilters = true;
            filters.append((veganFilter != null)? veganFilter:"").append(",");
            filters.append((streetFilter != null)? streetFilter:"").append(",");
            filters.append((fastFoodFilter != null)? veganFilter:"");
        }

        // get the info from db, for the specific restaurant.
        HashMap<String, Object> restInfos;
        HashMap<String, Object> restStars;
        HashMap<String, Object> ownerInfos;

        try {
            if (useFilters) {
                restInfos = queryManager.getFromDatabase(
                    filters.toString(),
                    Queries.SEARCH_REST_FILTERS.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    "restaurant_id",
                    "restaurant_owner",
                    "restaurant_name",
                    "restaurant_address",
                    "restaurant_phone",
                    "restaurant_pic"
                );
            } else {
                restInfos = queryManager.getFromDatabase(
                    name,
                    Queries.SEARCH_REST.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    "restaurant_id",
                    "restaurant_owner",
                    "restaurant_name",
                    "restaurant_address",
                    "restaurant_phone",
                    "restaurant_pic"
                );
            }

            ownerInfos = queryManager.getFromDatabase(
                    (Integer) restInfos.get("restaurant_owner"),
                    Queries.GET_OWNER_BY_OWNER_ID.getQuery(),
                    dbConnection.getConnection(),
                    "rest_user",
                    "username"
            );

            restStars = queryManager.getFromDatabase(
                    restInfos.get("restaurant_id"),
                    Queries.GET_STARS.getQuery(),
                    dbConnection.getConnection(),
                    "stared",
                    "stars"
            );

            request.getSession().setAttribute("rest_owner", (String) ownerInfos.get("username"));
            request.getSession().setAttribute("rest_name",  (String) restInfos.get("restaurant_name"));
            request.getSession().setAttribute("rest_addr",  (String) restInfos.get("restaurant_address"));
            request.getSession().setAttribute("rest_phone", (String) restInfos.get("restaurant_phone"));
            request.getSession().setAttribute("rest_pic",   (String) restInfos.get("restaurant_pic"));
            request.getSession().setAttribute("rest_id",    String.valueOf(restInfos.get("restaurant_id")));
            // Get stars.
            request.getSession().setAttribute("rest_stars", String.valueOf(restStars.get("stars")));

            dbConnection.closeConnection();
            response.sendRedirect(request.getContextPath()+"/restaurant/restaurant.jsp");
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
