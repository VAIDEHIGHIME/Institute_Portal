<%-- 
    Document   : Result
    Created on : Nov 16, 2018, 8:28:58 PM
    Author     : h p
--%>

<%@page import="com.DBMS.www.Assignment3.SupportingClasses.StudentRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


﻿<!DOCTYPE html>



<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IIITN PORTAL</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FONTAWESOME STYLES-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
     <!-- MORRIS CHART STYLES-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
        <!-- CUSTOM STYLES-->
    <link href="assets/css/custom.css" rel="stylesheet" />
     <!-- GOOGLE FONTS-->
   <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
</head>



<body >

    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">IIITN PORTAL</a> 
            </div>
        </nav>   
        <!-- /. NAV TOP  -->
		   
		   
		   
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li class="text-center">
                            <img src="assets/img/find_user.png" class="user-image img-responsive"/>
                    </li>	
                    <li>
                        <a   href="index.html"><i class="fa fa-dashboard fa-3x"></i> Dashboard</a>
                    </li>
                    <li>
                        <a class="active-menu" href="Search.jsp"><i class="fa fa-search fa-3x"></i> Search</a>
                    </li>
                    <li>
                        <a href="Courses.jsp"><i class="fa fa-bars fa-3x"></i> Courses</a>
                    </li>
                    <li>
                        <a  href="Failures.jsp"><i class="fa fa-ban fa-3x"></i> Failures</a>
                    </li>
                    <li>
                        <a  href="Register.jsp"><i class="fa fa-plus fa-3x"></i> Register</a>
                    </li>
                </ul>              
            </div>            
        </nav> 		
        <!-- /. NAV SIDE  -->
		
		
        <div id="page-wrapper" >
            <div id="page-inner">
                
                      <h3> Record Found  </h3>
        <% 
            ArrayList<StudentRecord> ResultList = (ArrayList<StudentRecord>)request.getAttribute("Result");

            for(StudentRecord record : ResultList)
            {
                out.print("Name: " + record.getName());
                out.print("<br/>");
                out.print("Department: " + record.getdepartment());
                out.print("<br/>");
                out.print("Registered Courses:");
                out.print("<br/>");
                for(int i=0;i<record.getRegisteredCourses().size();i++){
                     out.print("Course ID:"+record.getRegisteredCourses().get(i).getCourseId()+"\t"+"Course Title:"+record.getRegisteredCourses().get(i).getTitle()+"\t"+"Credit:"+record.getRegisteredCourses().get(i).getCredits()+"\t"+"Grade:"+record.getRegisteredCourses().get(i).getGrades());
                     out.print("<br/>");
                }
               
                
                
            }

        %>
      
                
             <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
         
         <a href="Search.jsp"><button>BACK</button></a>
        </div>
     <!-- /. WRAPPER  -->
	 
	 
	 
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
      <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
     <!-- MORRIS CHART SCRIPTS -->
     <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>
      <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
    
   
</body>
</html>

