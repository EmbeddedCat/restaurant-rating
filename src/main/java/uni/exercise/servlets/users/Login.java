package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;
import uni.exercise.servlets.security.TwoFactorAuth;
import uni.exercise.email.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Customer customer         = new Customer();
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();        

        String authcode_from_user;
        String gen_auth_code;
        int new_authcode;

        try {
            customer.login(request.getParameter("username"), request.getParameter("password"));

            // forward info to user profile.
            request.getSession().setAttribute("is_login", true);
            request.getSession().setAttribute("username", customer.getUsername());
            request.getSession().setAttribute("email", customer.getMail());
            request.getSession().setAttribute("address", customer.getAddress());

            authcode_from_user = (String) request.getParameter("user_input_auth_code");
            gen_auth_code = (String) request.getSession().getAttribute("original_auth_code");
            if (authcode_from_user != null && gen_auth_code != null) {
                if (authcode_from_user.equals(gen_auth_code)) {
                    response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
                    return;
                }
            } else {
                // generate a new auth code.
                new_authcode = TwoFactorAuth.getCode();
                // send an email with the new code. customer.getMail()
                EmailSender.sendEmail(Integer.toString(new_authcode), "");
                response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
                return;
            }

            if (queryManager.getFromDatabase(
                    customer.getUsername(),
                    Queries.RETRIEVE_DETAILS.getQuery(),
                    dbConnection.getConnection(),
                    "app_admin",
                    "username"
            ).isEmpty()) {
                request.getSession().setAttribute("role", "user");

                response.sendRedirect(request.getContextPath()+"/pages/user_page/user_page.jsp");
            } else {
                request.getSession().setAttribute("role", "admin");
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
