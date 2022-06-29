package uni.exercise.servlets.users;

import uni.exercise.users.Customer;
import uni.exercise.users.User;
import uni.exercise.users.user_exceptions.FailedToLogin;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Customer customer = new Customer();
        HashMap<String, String> userDetails;

        try {
            customer.login(request.getParameter("username"), request.getParameter("password"));

            /*userDetails = customer.getUserDetails(request.getParameter("username"));
            request.getSession().setAttribute("username", userDetails.get("username"));
            request.getSession().setAttribute("name", userDetails.get("name"));
            request.getSession().setAttribute("admin_userid", userDetails.get("admin_userid"));
            response.sendRedirect(request.getContextPath()+"");*/
            // TODO - redirect to user page.
        }
        catch (UserNotFound | FailedToLogin e) {
            throw new RuntimeException(e);
            // TODO - redirect to user failed login page ( remove the exception above ).
        }
        
    }
}
