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


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        SecurityManager securityManager = new SecurityManager();

        String enctypted_pass;
        try {
             enctypted_pass = securityManager.getHash(request.getParameter("password"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        try {
            queryManager.saveToDatabase(
                                Queries.ADD_USER.getQuery(),
                                dbConnection.getConnection(),
                                "rest_user",
                                request.getParameter("username"),
                                enctypted_pass,
                                request.getParameter("address"),
                                request.getParameter("email")
                               );
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}