<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../../css/user.css">
    <link rel="stylesheet" href="../../css/login_register.css">
    <link rel="stylesheet" href="../../css/index.css">
    <script src="../../js/verity_new_restaurant.js"></script>
    <script src="../../js/change_profile_info.js"></script>
</head>
<body>

    <%
        Boolean is_login = (Boolean) request.getSession().getAttribute("is_login");
        String username  = (String) request.getSession().getAttribute("username");
        String email     = (String) request.getSession().getAttribute("email");
        String address   = (String) request.getSession().getAttribute("address");

        if (is_login == null || username == null || email == null || address == null) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        } else if (is_login == false) {
            response.sendRedirect(request.getContextPath()+"/status/failed_page.jsp");
        }
    %>
    <!-- Header -->
    <div class="user-hero-image">

        <div class="topnav">
                <a class="active" href="../../index.jsp">Home</a>

                <form action="../../LogoutServlet" method="post">
                    <input type="submit" class="logout_btn" value="Logout">
                </form>
        </div>


        <div class="user-hero-center-image">
            <img src="../../images/profile_image.png" alt="profile image">
        </div>

        <div class="prof_username">
            <%=username%>
        </div>
    </div>
    <br><br><br><br>
    <br><br><br><br>
    <br><br><br><br>
    <br><br><br><br>


    <form action="../../UpdateDetails" method="post">
        <table id="details">
            <tr> 
                <th> username </th>
                <th> address </th>
                <th> email </th>
            </tr>c
            <tr>
                <td> <%=username%> </td>
                <td id="change_addr_td" onclick="change_address()">
                    <%=address%>
                </td>
                <td id="change_email_td" onclick="change_email()">
                    <%=email%> 
                </td>
            </tr>
        </table><br>
        <input type="hidden" id="change_addr" name="change_address_name" value=<%=address%>>
        <input type="hidden" id="change_email" name="change_email_name" value=<%=email%>>
        <input type="submit" class="register_btn" value="Save">
        <br><br><br><br><br>
    </form>

    <form action="../../AddRestaurant" method="post" onsubmit="verify_add_rest()">
        <div class="input_texts">
            <b>( Add your restaurant )</b>
        </div>
        <br><br><br>

        <div class="input_texts">
            <b>Restaurant name</b>
        </div>
        <input type="text" id="restaurant_name" name="rest_name" placeholder="Restaurant Name"><br>
        <div class="input_texts">
            <b>Restaurant address</b>
        </div>
        <input type="text" id="restaurant_address" name="rest_addr" placeholder="Restaurant Address"><br>
        <div class="input_texts">
            <b>Restaurant phone</b>
        </div>
        <input type="text" id="restaurant_phone" name="rest_phone" placeholder="Restaurant Phone"><br>
        <div class="input_texts">
            <b>Restaurant picture path</b>
        </div>
        <input type="text" id="restaurant_pic" name="rest_pic" placeholder="Restaurant picture"><br>

        <div class="search_filter">
            Vegan
            <input name="vegan" type="checkbox" value="vegan">
            Street
            <input name="street" type="checkbox" value="street">
            Fast Food
            <input name="fastfood" type="checkbox" value="fastfood">
        </div><br>

        <input type="submit" class="register_btn" value="Add">
    </form>

</body>
</html>
