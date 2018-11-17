package com.DBMS.www.Assignment3.SupportingClasses;

import java.util.ArrayList;

public class StudentRecord{
    
    private String name;
    private String department;
    private ArrayList<Course> coursesRegisteredInfo;
    
    //constructor
    public StudentRecord(){
        this.name=null;
        this.department=null;
        this.coursesRegisteredInfo=new ArrayList<>();
    }
    
    //getters
    public String getName(){
        return (this.name);
    }
    public String getdepartment(){
        return (this.department);
    }
    public ArrayList<Course> getRegisteredCourses(){
        return (this.coursesRegisteredInfo);
    }
    
    
    //setters
     public void setName(String name){
        this.name=name;
    }
    public void setDepartment(String department){
        this.department=department;
    }
    public void setCoursesRegisteredInfo(String couseId,String title,String credit,String grade){
        Course tempCourse=new Course();
        tempCourse.setCourseId(couseId);
        tempCourse.setTitle(title);
        tempCourse.setCredits(credit);
        tempCourse.setGrades(grade);
        this.coursesRegisteredInfo.add(tempCourse);
        
    }
    
    
    
    
     
}