package uni.exercise.servlets.users.restaurants;

import uni.exercise.db.DBConnection;
import uni.exercise.db.Queries;
import uni.exercise.db.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TopRestaurants extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        QueryManager queryManager = new QueryManager();
        DBConnection dbConnection = new DBConnection();

        ArrayList<HashMap<String, String>> restaurants;

        try {
            restaurants = queryManager.getMultipleColDB(
                    Queries.GET_RESTS.getQuery(),
                    dbConnection.getConnection(),
                    "restaurant"
            );

            for (int i = 0; i < 5; i++) {
                response.getWriter().println("" +
                        "            <div class=\"restaurants_align\">\n" +
                        "                <table class=\"restaurants_temp\">\n" +
                        "                    <tr>\n" +
                        "                        <th>\n" +
                        "                            <img src=\"images/"+restaurants.get(i).get("restaurant_pic")+"\" alt=\"no image\">\n" +
                        "                        </th>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td> "+restaurants.get(i).get("restaurant_owner")+" </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td> "+restaurants.get(i).get("restaurant_owner")+" </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td> "+restaurants.get(i).get("restaurant_address")+" </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td> "+restaurants.get(i).get("restaurant_phone")+" </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <th>\n" +
                        "                            <span class=\"fa fa-star checked star1\" onclick=\"test()\" ></span>\n" +
                        "                            <span class=\"fa fa-star checked star1.2\" onclick=\"test()\" style=\"display: none\" ></span>\n" +
                        "                            <span class=\"fa fa-star checked star2\"></span>\n" +
                        "                            <span class=\"fa fa-star checked star2.2\" onclick=\"test()\" style=\"display: none\"></span>\n" +
                        "                            <span class=\"fa fa-star checked star3\"></span>\n" +
                        "                            <span class=\"fa fa-star checked star3.2\" onclick=\"test()\" style=\"display: none\"></span>\n" +
                        "                            <span class=\"fa fa-star star4\"></span>\n" +
                        "                            <span class=\"fa fa-star checked star4.2\" onclick=\"test()\" style=\"display: none\" ></span>\n" +
                        "                            <span class=\"fa fa-star star5\"></span>\n" +
                        "                            <span class=\"fa fa-star checked star5.2\" onclick=\"test()\" style=\"display: none\"></span>\n" +
                        "                            <br><br>\n" +
                        "                            <form>\n" +
                        "                                <input class=\"star_number\" type=\"number\" style=\"display: none\">\n" +
                        "                                <input class=\"star_submit\" type=\"submit\" value=\"Submit\">\n" +
                        "                            </form>\n" +
                        "                        </th>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "            </div>");
            }

        } catch (SQLException e) {
            response.sendRedirect(request.getContextPath()+"status/failed_page.jsp");
        }

    }
}
