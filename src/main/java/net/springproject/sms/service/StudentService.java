package net.springproject.sms.service;

import jakarta.validation.Valid;
import net.springproject.sms.dto.StudentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
     List<StudentDto> getAllStudents();

    StudentDto createStudent(StudentDto student);

    StudentDto getStudentById(Long studentId);

    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);

    Page<StudentDto> getStudents(int page, int size, String sortBy);

    List<StudentDto> createStudents(List<StudentDto> students);


}
