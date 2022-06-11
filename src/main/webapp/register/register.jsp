<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/login_register.css">
</head>
<body>
    <!-- Header -->
    <div class="hero-image">
        <div class="topnav">
            <a href="../index.jsp">Home</a>
            <a href="../pages/contact.jsp">Contact</a>
            <a href="../pages/about.jsp">About</a>

            <div id="top_right">
                <a href="../login/login.jsp">Login</a>
                <a class="active"href="#register">Register</a>
            </div>
        </div>

        <div class="hero-text">
            <h1 style="font-size:50px">Restaurant Rating</h1>
        </div>

        <div class="login_register_title">
            <b>
                <a href="../login/login.jsp" class="hide_text">Login</a> / <a href="#register" class="show_text">Register</a>
            </b>
        </div>

        <!-- TODO - Here call the servlet -->
        <form>
            <div class="username">
                Username
            </div>
            <input type="text" placeholder="username">
            <div class="password">
                Password
            </div>
            <input type="password" placeholder="password">
            <div class="email_t">
                Email
            </div>
            <input class="email_input" placeholder="example@exp.com">
            <input type="button" class="register_btn" value="Register">
        </form>
    </div>
</body>
</html>
