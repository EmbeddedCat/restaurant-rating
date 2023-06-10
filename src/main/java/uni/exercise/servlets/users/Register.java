package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
import uni.exercise.security.SecurityManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.security.SecureRandom;

@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {

    @Override
    public void init() {

    }
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager       = new QueryManager();
        DBConnection dbConnection       = new DBConnection();
        SecureRandom secureRandom = new SecureRandom();

        byte[] salt = new byte[8]; // the salt to put in to the password. For security reasons.
        secureRandom.nextBytes(salt);

        StringBuilder salt_str = new StringBuilder(); // the salt as string.
        for (int i = 0; i < 8; i++) {
            salt_str.append((char) salt[i]);
        }

        StringBuilder newPassword = new StringBuilder();
        newPassword.append(request.getParameter("password"));
        newPassword.append(salt_str);

        String encryptedPass;
        try {
            encryptedPass = SecurityManager.getHash(newPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            queryManager.saveToDatabase(
                                Queries.ADD_USER.getQuery(),
                                dbConnection.getConnection(),
                                "rest_user",
                                request.getParameter("username"),
                                encryptedPass,
                                salt_str.toString(),
                                request.getParameter("address"),
                                request.getParameter("email")
                               );
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}