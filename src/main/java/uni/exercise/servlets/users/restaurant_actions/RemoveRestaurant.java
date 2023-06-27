package uni.exercise.servlets.users.restaurant_actions;

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

@WebServlet("/RemoveRestaurant")
public class RemoveRestaurant extends HttpServlet {
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
        // Get the restaurant address.
        String addr = request.getParameter("rest_name");
        String username = request.getParameter("username");

        try {
            if (queryManager.getFromDatabase(
                    username,
                    Queries.CHECK_IF_USER_IS_ADMIN.getQuery(),
                    dbConnection.getConnection(),
                    "app_admin",
                    "username"
            ).isEmpty()) {
                response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
            } else {
                // remove restaurant.
                queryManager.removeFromDB(
                        addr,
                        Queries.REMOVE_REST.getQuery(),
                        dbConnection.getConnection(),
                        "restaurant"
                );
                response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
            }
            dbConnection.closeConnection();
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }

    }
}
