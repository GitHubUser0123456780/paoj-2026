package com.pao.laboratory03.model;
import java.util.HashMap;

import com.pao.laboratory03.exceptions.InvalidGradeException;
import com.pao.laboratory03.exceptions.InvalidStudentException;
public class Student {
    private String name;
    private int age;
    private HashMap<Subject, Double> grades;
    public Student(String name, int age){
        if (age < 18 || age > 60)
            throw new InvalidStudentException("Varsta " + age + " nu este valida pentru un student.\n");
        this.name = name;
        this.age = age;
        grades = new HashMap<>();
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public HashMap<Subject,Double> getGrades(){
        return grades;
    }
    public void addGrade(Subject subiect, Double grade){
        if(grade<1.0 || grade>10.0)
            throw new InvalidGradeException("Nota " + grade + " nu este valida.\n");
        grades.put(subiect,grade);
    }
    public Double getAverage(){
        Double sum = 0.0;
        var elems = grades.values();
        for(var elem:elems)
            sum+=elem;
        sum/=elems.size();
        return sum;
    }
    public String toString(){
        return("Student{name='"+getName()+"', age="+getAge()+", avg="+getAverage()+"}\n");
    }
}
