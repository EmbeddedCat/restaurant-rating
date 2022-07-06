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

        try {
            customer.login(request.getParameter("username"), request.getParameter("password"));

            // forward info to user profile.
            request.getSession().setAttribute("is_login", true);
            request.getSession().setAttribute("username", customer.getUsername());
            request.getSession().setAttribute("email", customer.getMail());
            request.getSession().setAttribute("address", customer.getAddress());
            //response.sendRedirect(request.getContextPath()+"");
        }
        catch (UserNotFound | FailedToLogin e) {
            throw new RuntimeException(e);
            // TODO - redirect to user failed login page ( remove the exception above ).
        }
        
    }
}
