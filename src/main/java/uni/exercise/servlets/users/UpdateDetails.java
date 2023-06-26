package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/UpdateDetails")
public class UpdateDetails extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        String updatedEmail = (String) request.getParameter("change_email_name");
        String updatedAddr  = (String) request.getParameter("change_address_name");
        String username;
        // check wether the user if actually connected.
        Boolean isLoggedin = (Boolean) request.getSession().getAttribute("is_login");
        if (!isLoggedin) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }

        username = (String) request.getSession().getAttribute("username");
        try {
            // Store the updated values back to database.
            queryManager.saveToDatabase(
                    Queries.UPDATE_DETAILS.getQuery(),
                    dbConnection.getConnection(),
                    "rest_user",
                    updatedAddr,
                    updatedEmail,
                    username
            );
            request.getSession().setAttribute("email", updatedEmail);
            request.getSession().setAttribute("address", updatedAddr);
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
