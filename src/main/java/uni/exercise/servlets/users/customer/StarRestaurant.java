package uni.exercise.servlets.users.customer;

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

@WebServlet("/StarRestaurant")
public class StarRestaurant extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        String username = (String) request.getSession().getAttribute("username");
        String restaurant_addr = request.getParameter("restaurant_addr");

        if (username == null) {
            response.sendRedirect(request.getContextPath()+"/login/login.jsp");
            return;
        }

        try {
            queryManager.saveToDatabase(
                    Queries.USER_STAR_REST.getQuery(),
                    dbConnection.getConnection(),
                    "stared",
                    username,
                    restaurant_addr
            );
            dbConnection.closeConnection();
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }

    }
}
