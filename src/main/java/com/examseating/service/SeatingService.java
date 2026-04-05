package com.examseating.service;

import com.examseating.dto.SeatingRequest;
import com.examseating.dto.SeatingResponse;
import com.examseating.model.Room;
import com.examseating.model.Seating;
import com.examseating.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeatingService {

    private static final int ROWS_PER_ROOM    = 3;
    private static final int SEATS_PER_BENCH  = 2;

    private List<Seating> lastSeating = new ArrayList<>();

    /**
     * Allocates students to benches:
     *  - 3 rows per room
     *  - 2 students per bench (left + right)
     *  - Different branches paired on same bench where possible
     *  - Students sorted by rollNo ascending
     */
    public SeatingResponse generateSeating(SeatingRequest req) {
        List<Student> students = new ArrayList<>(req.getStudents());
        List<Room>    rooms    = req.getRooms();

        students.sort(Comparator.comparingInt(Student::getRollNo));

        List<Seating> allocationList = new ArrayList<>();
        int studentIndex = 0;

        for (Room room : rooms) {
            if (studentIndex >= students.size()) break;

            // How many benches we need for students assigned to this room
            // (seats fill up to room capacity: rows * cols / 2 benches)
            int benchesPerRow = room.getCols() / SEATS_PER_BENCH;
            if (benchesPerRow < 1) benchesPerRow = 1;

            // Collect students for this room
            int capacity = ROWS_PER_ROOM * benchesPerRow * SEATS_PER_BENCH;
            List<Student> roomStudents = new ArrayList<>();
            while (studentIndex < students.size() && roomStudents.size() < capacity) {
                roomStudents.add(students.get(studentIndex++));
            }

            // Allocate bench by bench, pairing different branches
            LinkedList<Student> queue = new LinkedList<>(roomStudents);

            for (int row = 1; row <= ROWS_PER_ROOM && !queue.isEmpty(); row++) {
                for (int bench = 1; bench <= benchesPerRow && !queue.isEmpty(); bench++) {

                    // Left seat
                    Student leftStu = queue.poll();
                    allocationList.add(new Seating(
                        leftStu.getRollNo(), leftStu.getName(), leftStu.getBranch(),
                        room.getName(), row, bench, "L"
                    ));

                    // Right seat — prefer different branch
                    if (!queue.isEmpty()) {
                        String leftBranch = leftStu.getBranch();
                        int diffIdx = -1;
                        for (int i = 0; i < queue.size(); i++) {
                            if (!Objects.equals(queue.get(i).getBranch(), leftBranch)) {
                                diffIdx = i; break;
                            }
                        }
                        Student rightStu = diffIdx >= 0
                            ? ((LinkedList<Student>) queue).remove(diffIdx)
                            : queue.poll();

                        allocationList.add(new Seating(
                            rightStu.getRollNo(), rightStu.getName(), rightStu.getBranch(),
                            room.getName(), row, bench, "R"
                        ));
                    }
                }
            }
        }

        lastSeating = allocationList;

        SeatingResponse res = new SeatingResponse();
        res.setAllocationList(allocationList);
        res.setTotalStudents(students.size());
        res.setTotalCapacity(rooms.stream().mapToInt(r -> ROWS_PER_ROOM * (r.getCols() / 2) * 2).sum());
        res.setUnallocated(Math.max(0, students.size() - allocationList.size()));
        return res;
    }

    public Optional<Seating> findByRollNo(int rollNo) {
        return lastSeating.stream().filter(s -> s.getRollNo() == rollNo).findFirst();
    }

    public List<Seating> getAll() {
        return Collections.unmodifiableList(lastSeating);
    }
}
