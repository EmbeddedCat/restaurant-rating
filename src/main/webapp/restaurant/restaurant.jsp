<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String owner = (String) request.getSession().getAttribute("rest_owner");
    String name  = (String) request.getSession().getAttribute("rest_name");
    String addr  = (String) request.getSession().getAttribute("rest_addr");
    String phone = (String) request.getSession().getAttribute("rest_phone");
    String pic   = (String) request.getSession().getAttribute("rest_pic");
    String stars = (String) request.getSession().getAttribute("rest_stars");
    String id    = (String) request.getSession().getAttribute("rest_id");
%>

<!DOCTYPE html>
<html>
<head>
      <title>Restaurant Rating</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="../css/index.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <!-- Header -->
    <div class="hero-image">
        <div class="topnav">
            <a class="active" href="../index.jsp">Home</a>
            <a href="../pages/contact.jsp">Contact</a>
            <a href="../pages/about.jsp">About</a>

            <div id="top_right">
                 <a href="../login/login.jsp">Login</a>
                 <a href="../register/register.jsp">Register</a>
            </div>

            </div>
                <div class="hero-text">
                    <h1 style="font-size:50px">Restaurant Rating</h1>
                </div>
            </div>
            <br><br><br>
            <br><br><br>
            <br><br><br>

            <center>
                <div class="restaurants_align">
                     <table class="restaurants_temp">
                         <tr>
                            <th>
                                <img src="../images/<%=pic%>">
                            </th>
                         </tr>
                         <tr>
                           <td> <%=owner%> </td>
                         </tr>
                         <tr>
                            <td> <%=name%> </td>
                         </tr>
                         <tr>
                            <td> <%=addr%> </td>
                         </tr>
                         <tr>
                            <td> <%=phone%> </td>
                         </tr>
                         <tr>
                             <td><span class="fa fa-star checked star1"></span> <%=stars%> </td>
                         </tr>
                         <tr>
                            <th>
                                <form action="../StarRestaurant" method="post">
                                     <input type=text name="restaurant_id" value="<%=id%>" style="Display: none;">
                                     <input class="star_submit" type="submit" value="You like it;">
                                </form>
                            </th>
                        </tr>
                    </table>
                </div>
            </center>

</body>
</html>
