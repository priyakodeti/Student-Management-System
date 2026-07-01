package net.springproject.sms.service.impl;

import net.springproject.sms.dto.StudentDto;
import net.springproject.sms.entity.Student;
import net.springproject.sms.mapper.StudentMapper;
import net.springproject.sms.repository.StudentRepository;
import net.springproject.sms.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    @Override
    public List<StudentDto> getAllStudents(){
       return studentRepository.findAll()
                .stream()
                .map(StudentMapper::mapToStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);
    }


    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return StudentMapper.mapToStudentDto(student);
    }


    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student updatedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudent);
    }
    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }


    @Override
    public Page<StudentDto> getStudents(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        Page<Student> students = studentRepository.findAll(pageable);

        return students.map(StudentMapper::mapToStudentDto);
    }

    @Override
    public List<StudentDto> createStudents(List<StudentDto> studentDtos) {

        List<Student> students = studentDtos.stream()
                .map(StudentMapper::mapToStudent)
                .toList();

        List<Student> savedStudents = studentRepository.saveAll(students);

        return savedStudents.stream()
                .map(StudentMapper::mapToStudentDto)
                .toList();
    }
}
