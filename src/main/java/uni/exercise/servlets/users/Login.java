package uni.exercise.servlets.users;

import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;
import uni.exercise.email.EmailSender;
import uni.exercise.security.SecurityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


import uni.exercise.servlets.users.email_user.EmailUser;


@WebServlet("/Login")
public class Login extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        Customer customer   = new Customer();
        String emailContent = "Your auth code is: ";
        int newTwoAuthCode;

        try {
            customer.login(request.getParameter("username"), request.getParameter("password"));
        }
        catch (UserNotFound e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        } 

        newTwoAuthCode = SecurityManager.getTwoAuthCode();
        request.getSession().setAttribute("systemAuthCode", String.valueOf(newTwoAuthCode));
        // Send an email with the auth code.
        emailContent = emailContent + String.valueOf(newTwoAuthCode);
        EmailSender.sendEmail(emailContent, customer.getMail());

        // forward info to user profile.
        request.getSession().setAttribute("is_login", false);
        request.getSession().setAttribute("username", customer.getUsername());
        request.getSession().setAttribute("email", customer.getMail());
        request.getSession().setAttribute("address", customer.getAddress());
        request.getSession().setAttribute("systemAuthCode", newTwoAuthCode);

        response.sendRedirect(request.getContextPath()+"/pages/auth/authentication.jsp");
    }
}
