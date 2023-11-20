package com.example.zoo_management_system;

import java.sql.*;
public class Employee
{
    private int emp_id;
    private String emp_name;
    private double salary;
    private Date dob;
    private int age;
    private String job;
    private int enc_id;

    public Employee(int emp_id, String emp_name, double salary, Date dob, int age, String job, int enc_id) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.salary = salary;
        this.dob = dob;
        this.age = age;
        this.job = job;
        this.enc_id = enc_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getEnc_id() {
        return enc_id;
    }

    public void setEnc_id(int enc_id) {
        this.enc_id = enc_id;
    }
}
