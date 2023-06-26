package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.users.Customer;
import uni.exercise.users.user_exceptions.UserNotFound;
import uni.exercise.security.SecurityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;


@WebServlet("/ChangePass")
public class ChangePass extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        SecureRandom secureRandom = new SecureRandom();

        String newPassword;
        String userEmail;
        String encryptedPass;
        StringBuilder encryptedPassBuilder;
        StringBuilder salt_str;
        byte[] salt;


        if (request.getSession().getAttribute("UserIsAuthorized") == null) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
            return;
        }

        newPassword = (String) request.getParameter("newPassword");
        userEmail = (String) request.getSession().getAttribute("email");
        if (newPassword == null || userEmail == null) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
            return;
        }
        
        request.getSession().setAttribute("email", null);
        request.getSession().setAttribute("UserIsAuthorized", null);
        request.getSession().setAttribute("requestedPassRecovery", null);

        // Build the new password.
        salt = new byte[8]; // the salt to put in to the password. For security reasons.
        secureRandom.nextBytes(salt);

        salt_str = new StringBuilder(); // the salt as string.
        for (int i = 0; i < 8; i++) {
            salt_str.append((char) salt[i]);
        }

        encryptedPassBuilder = new StringBuilder();
        encryptedPassBuilder.append(newPassword);
        encryptedPassBuilder.append(salt_str);

        try {
            encryptedPass = SecurityManager.getHash(encryptedPassBuilder.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            // Store the updated values back to database.
            queryManager.saveToDatabase(
                    Queries.CHANGE_PASSWORD.getQuery(),
                    dbConnection.getConnection(),
                    "rest_user",
                    encryptedPass,
                    salt_str.toString(),
                    userEmail
            );
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
