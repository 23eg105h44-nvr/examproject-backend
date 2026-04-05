package com.examseating.dto;
import com.examseating.model.Seating;
import java.util.List;
public class SeatingResponse {
    private List<Seating> allocationList;
    private int totalStudents;
    private int totalCapacity;
    private int unallocated;
    public List<Seating> getAllocationList() { return allocationList; }
    public void setAllocationList(List<Seating> v) { this.allocationList = v; }
    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int v) { this.totalStudents = v; }
    public int getTotalCapacity() { return totalCapacity; }
    public void setTotalCapacity(int v) { this.totalCapacity = v; }
    public int getUnallocated() { return unallocated; }
    public void setUnallocated(int v) { this.unallocated = v; }
}
