package uni.exercise.servlets.users.customer;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StarRestaurant extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        String username = request.getParameter("username");
        String restaurant = request.getParameter("restaurant");

        try {
            queryManager.saveToDatabase(
                    Queries.USER_STAR_REST.getQuery(),
                    dbConnection.getConnection(),
                    "stared",
                    username,
                    restaurant
            );
            // TODO - test.
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }

    }
}
