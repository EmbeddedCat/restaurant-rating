package uni.exercise.servlets.users;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        try {
            queryManager.saveToDatabase(
                                Queries.ADD_USER.getQuery(),
                                DBConnection.getConnection(),
                                "rest_user",
                                request.getParameter("username"),
                                request.getParameter("password"),
                                request.getParameter("address"),
                                request.getParameter("gmail")
                               );
            response.sendRedirect(request.getContextPath()+"status/success_page");
        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"status/failed_page");
        }
    }
}