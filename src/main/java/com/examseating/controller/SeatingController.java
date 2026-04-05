package com.examseating.controller;

import com.examseating.dto.SeatingRequest;
import com.examseating.dto.SeatingResponse;
import com.examseating.model.Seating;
import com.examseating.service.SeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SeatingController {

    @Autowired
    private SeatingService seatingService;

    /** POST /api/generateSeating — generate seating from students + rooms */
    @PostMapping("/generateSeating")
    public ResponseEntity<SeatingResponse> generate(@RequestBody SeatingRequest req) {
        if (req.getStudents() == null || req.getStudents().isEmpty())
            return ResponseEntity.badRequest().build();
        if (req.getRooms() == null || req.getRooms().isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(seatingService.generateSeating(req));
    }

    /** GET /api/student/{rollNo} — find one student's seat */
    @GetMapping("/student/{rollNo}")
    public ResponseEntity<Seating> getStudent(@PathVariable int rollNo) {
        return seatingService.findByRollNo(rollNo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    /** GET /api/seating — all current allocations */
    @GetMapping("/seating")
    public ResponseEntity<List<Seating>> getAll() {
        List<Seating> all = seatingService.getAll();
        return all.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(all);
    }
}
