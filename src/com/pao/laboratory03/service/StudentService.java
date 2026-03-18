package com.pao.laboratory03.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.pao.laboratory03.exceptions.StudentNotFoundException;
import com.pao.laboratory03.model.Student;
import com.pao.laboratory03.model.Subject;
public class StudentService {
    private static StudentService singl_instance = null;
    private List<Student> studenti;
    private StudentService(){
        studenti = new ArrayList<>();
    }
    public static synchronized StudentService getInstance(){
        if(singl_instance == null)
            singl_instance = new StudentService();
        return singl_instance;
    }
    public void addStudent(String name, int age){
        Student student_nou = new Student(name, age);
        for(Student s:studenti){
            if(s.getName().equals(name))
                throw new RuntimeException("Studentul " + name + " este deja in lista.\n");
        }
        studenti.add(student_nou);
    }
    public Student findByName(String name){
        for(Student s:studenti){
            if(s.getName().equals(name))
                return s;
        }
        throw new StudentNotFoundException("Studentul cu numele " + name + " nu a fost gasit.\n");
    }
    public void addGrade(String student_name, Subject subject_name, Double grade){
        Student student = findByName(student_name);
        student.addGrade(subject_name, grade);
    }
    public void printAllStudents(){
        for(Student s:studenti)
            System.out.println(s);
    }
    public void printTopStudents(){
        studenti.stream()
            .sorted((s1,s2) -> Double.compare(s2.getAverage(),s1.getAverage()))
            .forEach(s1 -> System.out.println(s1));
    }
    public HashMap<Subject,Double> getAveragePerSubject(){
        HashMap<Subject,Integer> Subject_count = new HashMap<>();
        HashMap<Subject,Double> Subject_sum = new HashMap<>();
        for(Student s:studenti){
            HashMap<Subject,Double> returned_grades = s.getGrades();
            for(var elem:returned_grades.entrySet())
            {
                Subject_count.put(elem.getKey(), Subject_count.getOrDefault(elem.getKey(), 0)+1);
                Subject_sum.put(elem.getKey(), Subject_sum.getOrDefault(elem.getKey(), 0.0)+elem.getValue());
            }
        }
        HashMap<Subject,Double> Result = new HashMap<>();
        for(var e:Subject_count.entrySet())
            Result.put(e.getKey(),Subject_sum.get(e.getKey())/Subject_count.get(e.getKey()));
        return Result;
    }
}
