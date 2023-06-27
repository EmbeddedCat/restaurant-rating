<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/index.css">
    <link rel="stylesheet" href="../../css/login_register.css">
    <script src="../../js/verify_inputs.js"></script>
</head>
<body>

    <!-- Header -->
    <div class="hero-image">
        <div class="topnav">
            <a href="../../index.jsp">Home</a>
            <a href="../../pages/contact.jsp">Contact</a>
            <a href="../../pages/about.jsp">About</a>

            <div id="top_right">
                <a class="active" href="#login">Login</a>
                <a href="../../register/register.jsp">Register</a>
            </div>

        </div>

        <div class="hero-text">
            <h1 style="font-size:50px">Rate2Eat</h1>
        </div>
    </div>

    <div class="login_register_title">
        <b>
            <a href="#login" class="show_text" >Login</a> / <a href="../../register/register.jsp" class="hide_text">Register</a> / 
            <a href="passwordrecovery.jsp" class="hide_text" >Password Recovery</a>
        </b>
    </div><br><br><br><br><br><br><br><br><br>

    <div>
        <!-- TODO - Here call the servlet-->
        <form action="../../ChangePass" method="post" onsubmit="verify_change_passwd()">
            <div class="input_texts">
                <b>New password</b>
            </div>
            <input type="password" id="new_password" name="newPassword" placeholder="New password"><br>
            <div class="input_texts">
                <b>New password again</b>
            </div>
            <input type="password" id="new_password_again" name="newPasswordAgain" placeholder="New password again"><br><br>
            <input type="submit" class="login_btn" value="Login">
        </form>
    </div>
</body>
</html>
