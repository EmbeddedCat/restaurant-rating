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
        HashMap<String, String> rest_infos;
        HashMap<String, String> rest_stars;

        try {
            if (useFilters) {
                rest_infos = queryManager.getFromDatabase(
                    filters.toString(),
                    Queries.SEARCH_REST_FILTERS.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    "restaurant_name",
                    "restaurant_Owner",
                    "restaurant_address",
                    "restaurant_phone",
                    "restaurant_pic"
                );
            } else {
                rest_infos = queryManager.getFromDatabase(
                    name,
                    Queries.SEARCH_REST_FILTERS.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    "restaurant_name",
                    "restaurant_Owner",
                    "restaurant_address",
                    "restaurant_phone",
                    "restaurant_pic"
                );

            }

            rest_stars = queryManager.getFromDatabase(
                    rest_infos.get("restaurant_address"),
                    Queries.GET_STARS.getQuery(),
                    dbConnection.getConnection(),
                    "stared",
                    "stars"
            );

            request.getSession().setAttribute("rest_owner", rest_infos.get("restaurant_owner"));
            request.getSession().setAttribute("rest_name", rest_infos.get("restaurant_name"));
            request.getSession().setAttribute("rest_addr", rest_infos.get("restaurant_address"));
            request.getSession().setAttribute("rest_phone", rest_infos.get("restaurant_phone"));
            request.getSession().setAttribute("rest_pic", rest_infos.get("restaurant_pic"));
            // Get stars.
            request.getSession().setAttribute("rest_stars", rest_stars.get("stars"));

            dbConnection.closeConnection();
            response.sendRedirect(request.getContextPath()+"/restaurant/restaurant.jsp");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
