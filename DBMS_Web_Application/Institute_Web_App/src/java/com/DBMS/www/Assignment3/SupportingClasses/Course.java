
package com.DBMS.www.Assignment3.SupportingClasses;

public class Course {
    private String courseId;
    private String title;
    private String credits;
    private String grades;
    
    //constructor
    Course(){
        this.courseId=null;
        this.credits=null;
        this.grades=null;
        this.title=null;
    }
    
    
    //setters
    public void setCourseId(String courseId){
        this.courseId=courseId;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setCredits(String credits){
        this.credits=credits;
    }
    public void setGrades(String grades){
        this.grades=grades;
    }
    
    //getters
    public String getCourseId(){
        return (this.courseId);
    }
    public String getTitle(){
        return (this.title);
    }
    public String getCredits(){
        return (this.credits);
    }
    public String getGrades(){
        return (this.grades);
    }
    
}
