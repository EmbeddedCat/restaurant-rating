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


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Customer customer         = new Customer();
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        try {
            customer.login(request.getParameter("username"), request.getParameter("password"));

            // forward info to user profile.
            request.getSession().setAttribute("is_login", true);
            request.getSession().setAttribute("username", customer.getUsername());
            request.getSession().setAttribute("email", customer.getMail());
            request.getSession().setAttribute("address", customer.getAddress());

            if (queryManager.getFromDatabase(
                    customer.getUsername(),
                    Queries.RETRIEVE_DETAILS.getQuery(),
                    dbConnection.getConnection(),
                    "app_admin",
                    "username"
            ).isEmpty()) {
                response.sendRedirect(request.getContextPath()+"/pages/user_page/user_page.jsp");
            } else {
                response.sendRedirect(request.getContextPath()+"/pages/user_page/admin_page.jsp");
            }
        }
        catch (UserNotFound e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
