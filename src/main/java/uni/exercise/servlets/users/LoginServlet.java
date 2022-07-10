package uni.exercise.servlets.users;

import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        }
        catch (UserNotFound e) {
            response.sendRedirect(request.getContextPath()+"status/failed_page.jsp");
        }
        
    }
}
