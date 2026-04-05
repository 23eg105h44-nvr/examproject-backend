package com.examseating.model;
public class Seating {
    private int rollNo; private String name; private String branch;
    private String room; private int benchRow; private int benchNum; private String side;
    public Seating() {}
    public Seating(int rollNo, String name, String branch, String room, int benchRow, int benchNum, String side) {
        this.rollNo=rollNo; this.name=name; this.branch=branch;
        this.room=room; this.benchRow=benchRow; this.benchNum=benchNum; this.side=side;
    }
    public int getRollNo() { return rollNo; } public void setRollNo(int v) { this.rollNo=v; }
    public String getName() { return name; } public void setName(String v) { this.name=v; }
    public String getBranch() { return branch; } public void setBranch(String v) { this.branch=v; }
    public String getRoom() { return room; } public void setRoom(String v) { this.room=v; }
    public int getBenchRow() { return benchRow; } public void setBenchRow(int v) { this.benchRow=v; }
    public int getBenchNum() { return benchNum; } public void setBenchNum(int v) { this.benchNum=v; }
    public String getSide() { return side; } public void setSide(String v) { this.side=v; }
}
