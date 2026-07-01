package net.springproject.sms.controller;

import jakarta.validation.Valid;
import net.springproject.sms.dto.StudentDto;
import net.springproject.sms.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET all students
    @GetMapping
    public List<StudentDto> listStudents() {
        return studentService.getAllStudents();
    }

    // GET student by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    // CREATE student
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(
            @Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    // UPDATE student
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        studentDto.setId(id);
        return ResponseEntity.ok(studentService.updateStudent(studentDto));
    }

    // DELETE student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }


    @GetMapping("/page")
    public Page<StudentDto> getStudents(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {

        return studentService.getStudents(page, size, sortBy);
    }
    @PostMapping("/bulk")
    public List<StudentDto> createStudents(@RequestBody List<StudentDto> students) {
        return studentService.createStudents(students);
    }

    


}