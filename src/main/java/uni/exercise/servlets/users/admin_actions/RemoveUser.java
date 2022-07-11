package uni.exercise.servlets.users.admin_actions;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RemoveUser extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();
        String username = request.getParameter("username");

        try {
            queryManager.removeFromDB(
                    username,
                    Queries.REMOVE_USER.getQuery(),
                    dbConnection.getConnection(),
                    "rest_user"
            );
            // TODO - tests.
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
