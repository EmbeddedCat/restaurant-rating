<%@ page import="uni.exercise.db.QueryManager" %>
<%@ page import="uni.exercise.db.DBConnection" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="uni.exercise.db.Queries" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


            <%
                response.getWriter().print("" +
                        "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Restaurant Rating</title>\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "    <link rel=\"stylesheet\" href=\"./css/index.css\">\n" +
                        "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<!-- Header -->\n" +
                        "<div class=\"hero-image\">\n" +
                        "    <div class=\"topnav\">\n" +
                        "        <a class=\"active\" href=\"#home\">Home</a>\n" +
                        "        <a href=\"./pages/contact.jsp\">Contact</a>\n" +
                        "        <a href=\"./pages/about.jsp\">About</a>\n" +
                        "\n" +
                        "        <div id=\"top_right\">\n" +
                        "            <a href=\"./login/login.jsp\">Login</a>\n" +
                        "            <a href=\"./register/register.jsp\">Register</a>\n" +
                        "        </div>\n" +
                        "\n" +
                        "    </div>\n" +
                        "\n" +
                        "    <div class=\"hero-text\">\n" +
                        "        <h1 style=\"font-size:50px\">Restaurant Rating</h1>\n" +
                        "    </div>\n" +
                        "</div>\n" +
                        "<br><br><br>\n" +
                        "<br><br><br>\n" +
                        "<br><br><br>\n" +
                        "\n" +
                        "<div>\n" +
                        "    <form>\n" +
                        "        <div id=\"search_rest_text\">\n" +
                        "            Restaurant name\n" +
                        "        </div><br><br>\n" +
                        "        <div>\n" +
                        "            <input class=\"search_input\" type=\"text\" placeholder=\"Restaurant name\"><br>\n" +
                        "            <input class=\"search_button\" type=\"submit\" value=\"Search\">\n" +
                        "        </div>\n" +
                        "    </form>\n" +
                        "</div><br><br><br><br>\n" +
                        "<br><br><br><br>\n" +
                        "<br><br><br><br>");

                QueryManager queryManager = new QueryManager();
                DBConnection dbConnection = new DBConnection();

                ArrayList<HashMap<String, String>> restaurants = null;

                try {
                    restaurants = queryManager.getMultipleColDB(
                            Queries.GET_RESTS.getQuery(),
                            dbConnection.getConnection(),
                            "restaurant",
                            "restaurant_owner",
                            "restaurant_name",
                            "restaurant_address",
                            "restaurant_phone",
                            "restaurant_pic"
                    );
                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
                }

                if (restaurants != null && !restaurants.isEmpty()) {
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
                                    "                        <td> "+restaurants.get(i).get("restaurant_name")+" </td>\n" +
                                    "                    </tr>\n" +
                                    "                    <tr>\n" +
                                    "                        <td> "+restaurants.get(i).get("restaurant_address")+" </td>\n" +
                                    "                    </tr>\n" +
                                    "                    <tr>\n" +
                                    "                        <td> "+restaurants.get(i).get("restaurant_phone")+" </td>\n" +
                                    "                    </tr>\n" +
                                    "                    <tr>\n" +
                                    "                        <th>\n" +
                                    "                            <form>\n" +
                                    "                                <input class=\"star_submit\" type=\"submit\" value=\"You like it;\">\n" +
                                    "                            </form>\n" +
                                    "                        </th>\n" +
                                    "                    </tr>\n" +
                                    "                </table>\n" +
                                    "            </div>");
                        }
                }

                response.getWriter().println("</body>" +
                        "</html>");
                dbConnection.closeConnection();
            %>


            <!-- TODO show 5 restaurants -->
            <!-- one restaurant
            <div class="restaurants_align">
                <table class="restaurants_temp">
                    <tr>
                        <th>
                            <img src="images/noimage.jpeg" alt="no image">
                        </th>
                    </tr>
                    <tr>
                        <td> owner </td>
                    </tr>
                    <tr>
                        <td> name </td>
                    </tr>
                    <tr>
                        <td> address </td>
                    </tr>
                    <tr>
                        <td> phone </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="fa fa-star checked star1" onclick="test()" ></span>
                            <span class="fa fa-star checked star1.2" onclick="test()" style="display: none" ></span>
                            <span class="fa fa-star checked star2"></span>
                            <span class="fa fa-star checked star2.2" onclick="test()" style="display: none"></span>
                            <span class="fa fa-star checked star3"></span>
                            <span class="fa fa-star checked star3.2" onclick="test()" style="display: none"></span>
                            <span class="fa fa-star star4"></span>
                            <span class="fa fa-star checked star4.2" onclick="test()" style="display: none" ></span>
                            <span class="fa fa-star star5"></span>
                            <span class="fa fa-star checked star5.2" onclick="test()" style="display: none"></span>
                            <br><br>
                            <form>
                                <input class="star_number" type="number" style="display: none">
                                <input class="star_submit" type="submit" value="Submit">
                            </form>
                        </th>
                    </tr>
                </table>
            </div>
             -->