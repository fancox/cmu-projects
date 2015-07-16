<!-- @author fanc -->
<!-- error.jsp -->
<% String email=(String) request.getAttribute("email"); 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>welcome</title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <style type="text/css">
            body {
                padding-top: 60px;
                padding-bottom: 40px;
            }
            .sidebar-nav {
                padding: 9px 0;
            }
        </style>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <a class="brand" href="Welcome">Instacram</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li><a href="YourCourses">Your courses</a></li>
                            <li><a href="BrowseCourses">Browse courses</a></li>
                            <li><a href="CreateCourse">Create a course</a></li>
                            <li><a href="SearchNote">Search Note</a></li>
                            <li><a href="Logoff">Log off!</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="hero-unit">
                        <div class="row">
                            <div class="span9">
                                Sorry! Please check your input and register again!
                            </div><!--/span-->
                        </div><!--/row--> 
                    </div><!--/row-->
                </div><!--/row--> 
            </div><!--/row-->
            <footer> <p>
                    &copy; 2012 - <b>Fan Chen</b>, Carnegie Mellon University
                </p></footer>
        </div><!--/.fluid-container-->
    </body>
</html>
