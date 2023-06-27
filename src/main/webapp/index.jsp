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
                        "    <form action="+request.getContextPath()+"/Search method=\"post\">\n" +
                        "        <div id=\"search_rest_text\">\n" +
                        "            Restaurant name\n" +
                        "        </div><br><br>\n" +
                        "        <div>\n" +
                        "            <input class=\"search_input\" name=\"rest_name\" type=\"text\" placeholder=\"Restaurant name\"><br>\n" +
                        "            <div class=\"search_filter\">Vegan\n" +
                        "            <input name=\"vegan\" type=\"checkbox\" value=\"vegan\">\n" +
                        "            Street\n" +
                        "            <input name=\"street\" type=\"checkbox\" value=\"street\">\n" +
                        "            Fast Food\n" +
                        "            <input name=\"fastfood\" type=\"checkbox\" value=\"fastfood\"></div><br>\n" +
                        "            <input class=\"search_button\" type=\"submit\" value=\"Search\">\n" +
                        "        </div>\n" +
                        "    </form>\n" +
                        "</div><br><br><br><br>\n" +
                        "<br><br><br><br>\n" +
                        "<br><br><br><br>");

                QueryManager queryManager = new QueryManager();
                DBConnection dbConnection = new DBConnection();

                ArrayList<HashMap<String, String>> restaurants = null;
                HashMap<String, String> rest_stars;

                try {
                    restaurants = queryManager.getMultipleColDB(
                            Queries.GET_RESTS.getQuery(),
                            dbConnection.getConnection(),
                            "restaurant",
                            "restaurant_name",
                            "restaurant_address",
                            "restaurant_phone",
                            "restaurant_pic"
                    );

                } catch (SQLException e) {
                    response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
                    return;
                }

                if (restaurants != null && !restaurants.isEmpty()) {
                        for (int i = 0; i < 7; i++) {
                            response.getWriter().println("" +
                                    "            <div class=\"restaurants_align\">\n" +
                                    "                <table class=\"restaurants_temp\">\n" +
                                    "                    <tr>\n" +
                                    "                        <th>\n" +
                                    "                            <img src=\"images/"+restaurants.get(i).get("restaurant_pic")+"\" alt=\"no image\">\n" +
                                    "                        </th>\n" +
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
                                    "                            <form action=\""+request.getContextPath()+"/StarRestaurant\" method=\"post\">\n" +
                                    "                                <input type=text name=\"restaurant_addr\" value=\""+restaurants.get(i).get("restaurant_address")+"\" style=\"Display: none;\">" +
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
            %>