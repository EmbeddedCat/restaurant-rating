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

@WebServlet("/AddRestaurant")
public class AddRestaurant extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        // Get restaurant infos.
        String owner = (String) request.getSession().getAttribute("username");
        String name  = request.getParameter("rest_name");
        String addr  = request.getParameter("rest_addr");
        String phone = request.getParameter("rest_phone");
        String pic   = request.getParameter("rest_pic");

        try {
            // save restaurant infos.
            queryManager.saveToDatabase(
                    Queries.ADD_REST.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    owner,
                    name,
                    addr,
                    phone,
                    pic
            );
            dbConnection.closeConnection();
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
