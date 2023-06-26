package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;
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

@WebServlet("/AuthValidator")
public class AuthValidator extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();        

        String username;
        String authCodeFromSystem;
        String authCodeFromUser;

        authCodeFromSystem = (String) String.valueOf(request.getSession().getAttribute("systemAuthCode"));
        authCodeFromUser = (String) request.getParameter("userAuthCode");
        request.getSession().setAttribute("systemAuthCode", 0);

        // If the authentication not succeed, then don't allow the user to login.
        if (!authCodeFromSystem.equals(authCodeFromUser)) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
            return;
        }
        request.getSession().setAttribute("UserIsAuthorized", 1);

        if (request.getSession().getAttribute("requestedPassRecovery") != null) {
            response.sendRedirect(request.getContextPath()+"/pages/passrecovery/changepassword.jsp");
            return;
        }

        try {
            username = (String) request.getSession().getAttribute("username");
            if (username == null) {
                response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
                return;
            }

            request.getSession().setAttribute("is_login", true);

            if (queryManager.getFromDatabase(
                    username,
                    Queries.CHECK_IF_USER_IS_ADMIN.getQuery(),
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
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
