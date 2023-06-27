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
import java.util.HashMap;

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
        String restaurantId = request.getParameter("restaurant_id");
        String username = (String) request.getSession().getAttribute("username");

        Integer commiterId;

        if (username == null) {
            response.sendRedirect(request.getContextPath()+"/login/login.jsp");
            return;
        }

        HashMap<String, Object> commiterInfos;

        try {

            commiterInfos = queryManager.getFromDatabase(
                    username,
                    Queries.GET_OWNER.getQuery(),
                    dbConnection.getConnection(),
                    "rest_user",
                    "user_id"
            );
            commiterId = (Integer) commiterInfos.get("user_id");

            queryManager.saveToDatabase(
                    Queries.USER_STAR_REST.getQuery(),
                    dbConnection.getConnection(),
                    "stared",
                    commiterId,
                    Integer.parseInt(restaurantId)
            );
            dbConnection.closeConnection();
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }

    }
}
