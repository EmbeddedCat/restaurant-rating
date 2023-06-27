package uni.exercise.servlets.users.restaurant_actions;

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
import java.util.HashMap;


@WebServlet("/AddRestaurant")
public class AddRestaurant extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        // Get restaurant infos.
        String owner_username = (String) request.getSession().getAttribute("username");
        String name           = (String) request.getParameter("rest_name");
        String addr           = (String) request.getParameter("rest_addr");
        String phone          = (String) request.getParameter("rest_phone");
        String pic            = (String) request.getParameter("rest_pic");
        String veganFilter    = (String) request.getParameter("vegan");
        String streetFilter   = (String) request.getParameter("street");
        String fastFoodFilter = (String) request.getParameter("fastfood");

        Integer owner_id;
        HashMap<String, Object> owner_info;

        StringBuilder filters = new StringBuilder();
        filters.append((veganFilter != null)? veganFilter:"").append(",");
        filters.append((streetFilter != null)? streetFilter:"").append(",");
        filters.append((fastFoodFilter != null)? veganFilter:"");

        try {
            owner_info = queryManager.getFromDatabase(
                owner_username,
                Queries.GET_OWNER.getQuery(),
                dbConnection.getConnection(),
                "rest_user",
                "user_id"
            );
            owner_id = (Integer) owner_info.get("user_id");

            // save restaurant infos.
            queryManager.saveToDatabase(
                    Queries.ADD_REST.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant",
                    owner_id,
                    name,
                    addr,
                    phone,
                    pic,
                    filters.toString()
            );
            dbConnection.closeConnection();
            response.sendRedirect(request.getContextPath()+"/status/success_page.jsp");
        } catch (SQLException e) {
            System.out.println(e);
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    }
}
