package com.examseating.model;
public class Student {
    private int rollNo; private String name; private String branch;
    public Student() {}
    public Student(int r, String n, String b) { this.rollNo=r; this.name=n; this.branch=b; }
    public int getRollNo() { return rollNo; } public void setRollNo(int v) { this.rollNo=v; }
    public String getName() { return name; }  public void setName(String v) { this.name=v; }
    public String getBranch() { return branch; } public void setBranch(String v) { this.branch=v; }
}
