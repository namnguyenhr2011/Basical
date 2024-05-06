<%-- 
    Document   : register
    Created on : Mar 8, 2024, 12:29:23 PM
    Author     : WanaW
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="images/icons/logo.png" />
        <title>BASICAL</title>
    </head>
    <body>
        <div id="bg"></div>
        <form action="signup" method="POST">
            <h1 style="color: white">Signup</h1>
            <div class="form-field-reg">
                <input type="text" name="fullname" placeholder="Full name" required />
            </div>
            <div class="form-field-reg">
                <input type="text" name="username" placeholder="Username" required />
            </div>
            <div class="form-field-reg">
                <input type="password" name="password" placeholder="Password" required />
            </div>
            <div class="form-field-reg">
                <input type="email" name="email" placeholder="Email" required />
            </div>
            ${message}
            <div class="form-field-reg">
                <button class="btn" type="submit">Sign up</button>
            </div>
            <div class="footer-form">You have account? <a href="login.jsp">Login</a></div>
        </form>
    </body>
    <style>
        @import url("https://fonts.googleapis.com/css?family=Lato:400,700");
        #bg {
            background-image: url('images/background.png');
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-size: cover;
            filter: blur(5px);
        }

        body {
            font-family: 'Lato', sans-serif;
            color: #4A4A4A;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            overflow: hidden;
            margin: 0;
            padding: 0;
        }

        form {
            width: 350px;
            position: relative;
        }

        form .form-field-reg::before {
            font-size: 20px;
            position: absolute;
            left: 15px;
            top: 17px;
            color: #888888;
            content: " ";
            display: block;
            background-size: cover;
            background-repeat: no-repeat;
        }
        form .form-field-reg:nth-child(2)::before {
            background-image: url(images/icons/name-icon.png);
            width: 20px;
            height: 20px;
        }

        form .form-field-reg:nth-child(3)::before {
            background-image: url(images/icons/user-icon.png);
            width: 20px;
            height: 20px;
            top: 15px;
        }

        form .form-field-reg:nth-child(4)::before {
            background-image: url(images/icons/key.png);
            width: 16px;
            height: 16px;
        }

        form .form-field-reg:nth-child(5)::before {
            background-image: url(images/icons/email.png);
            width: 20px;
            height: 20px;
        }

        form .form-field-reg {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            margin-bottom: 1rem;
            position: relative;
        }

        form input {
            font-family: inherit;
            width: 100%;
            outline: none;
            background-color: #fff;
            border-radius: 4px;
            border: none;
            display: block;
            padding: 0.9rem 0.7rem;
            box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
            font-size: 17px;
            color: #4A4A4A;
            text-indent: 40px;
        }

        form .btn {
            outline: none;
            border: none;
            cursor: pointer;
            display: inline-block;
            margin: 0 auto;
            padding: 0.9rem 2.5rem;
            text-align: center;
            background-color: #47AB11;
            color: #fff;
            border-radius: 4px;
            box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
            font-size: 17px;
        }

        .footer-form {
            text-align: center;
        }
    </style>
</html>
