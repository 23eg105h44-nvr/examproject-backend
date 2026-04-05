package com.examseating.dto;
import com.examseating.model.Student;
import com.examseating.model.Room;
import java.util.List;
public class SeatingRequest {
    private List<Student> students;
    private List<Room> rooms;
    public List<Student> getStudents() { return students; }
    public void setStudents(List<Student> v) { this.students = v; }
    public List<Room> getRooms() { return rooms; }
    public void setRooms(List<Room> v) { this.rooms = v; }
}
