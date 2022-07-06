<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Restaurant Rating</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="./css/index.css">
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
                    <div id="search_rest">
                        <input type="text" placeholder="Restaurant name"><br>
                        <input type="submit" value="Search">
                    </div>
                </form>
            </div>

            <!-- TODO show 5 restaurants -->
    </body>
</html>