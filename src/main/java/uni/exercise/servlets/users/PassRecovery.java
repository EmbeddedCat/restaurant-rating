package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;
import uni.exercise.security.SecurityManager;
import uni.exercise.email.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PassRecovery")
public class PassRecovery extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String emailContent = "Your auth code is: ";
        int newTwoAuthCode;
        String userEmail = (String) request.getParameter("passwordRecoveryEmail");


        newTwoAuthCode = SecurityManager.getTwoAuthCode();
        request.getSession().setAttribute("systemAuthCode", String.valueOf(newTwoAuthCode));
        // Send an email with the auth code.
        emailContent = emailContent + String.valueOf(newTwoAuthCode);
        EmailSender.sendEmail(emailContent, userEmail);

        request.getSession().setAttribute("email", userEmail);
        request.getSession().setAttribute("requestedPassRecovery", 1);

        response.sendRedirect(request.getContextPath()+"/pages/auth/authentication.jsp");
            
    }
}
