<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/index.css">
    <link rel="stylesheet" href="../css/login_register.css">
    <script src="../js/verify_inputs.js"></script>
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
    </div>
    <div class="login_register_title">
        <b>
            <a href="../login/login.jsp" class="hide_text">Login</a> / <a href="#register" class="show_text">Register</a>
        </b>
    </div><br><br><br><br><br><br><br><br><br>

    <div>
        <form action="../RegisterServlet" method="post" onsubmit="verify_register()">
            <div class="input_texts">
                <b>Username</b>
            </div>
            <input type="text" id="register_username" name="username" placeholder="username"><br>
            <div class="input_texts">
                <b>Password</b>
            </div>
            <input type="password" id="register_password"  name="password" placeholder="password"><br>
            <div class="input_texts">
                <b>Password Verify</b>
            </div>
            <input type="password" id="register_password_v" placeholder="password verification"><br>
            <div class="input_texts">
                <b>Email</b>
            </div>
            <input type="email" id="register_email" name="gmail" placeholder="example@exp.com"><br>
            <div class="input_texts">
                <b>Address</b>
            </div>
            <input type="text" id="register_address" name="address" placeholder="Your address"><br><br>

            <input type="submit" class="register_btn" value="Register">
        </form>
    </div>
</body>
</html>
