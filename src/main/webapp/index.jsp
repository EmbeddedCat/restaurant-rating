<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Restaurant Rating</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./css/index.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
            <!-- Header -->
            <div class="hero-image">
                <div class="topnav">
                    <a class="active" href="#home">Home</a>
                    <a href="./pages/contact.jsp">Contact</a>
                    <a href="./pages/about.jsp">About</a>

                    <div id="top_right">
                        <a href="./login/login.jsp">Login</a>
                        <a href="./register/register.jsp">Register</a>
                    </div>

                </div>

                <div class="hero-text">
                    <h1 style="font-size:50px">Restaurant Rating</h1>
                </div>
            </div>
            <br><br><br>
            <br><br><br>
            <br><br><br>

            <div>
                <form>
                    <div id="search_rest_text">
                        Restaurant name
                    </div><br><br>
                    <div>
                        <input class="search_input" type="text" placeholder="Restaurant name"><br>
                        <input class="search_button" type="submit" value="Search">
                    </div>
                </form>
            </div><br><br><br><br>
            <br><br><br><br>
            <br><br><br><br>

            <!-- TODO show 5 restaurants -->
            <!-- one restaurant -->
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
    </body>
</html>