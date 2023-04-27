package com.main.neosoft.service.impl;

import com.main.neosoft.dto.StudentDto;
//import com.main.neosoft.entity.Student;
import com.main.neosoft.exception.ResourceNotFoundException;
//import com.main.neosoft.repository.StudentRepository;
import com.main.neosoft.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

//    @Autowired
//    private StudentRepository studentRepository;

//    @Override
//    public Student addStudent(Student student) {
//
//        return studentRepository.save(student);
//    }
//
//    @Override
//    public Student getStudentById(Long studentId) {
//        return studentRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found with id: " +studentId));
//
//    }
//
//    @Override
//    public List<Student> getAllStudents() {
//        return studentRepository.findAll();
//
//    }
//
//    @Override
//    public void deleteStudentById(Long studentId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Student Not Found with id: " +studentId));
//
//        studentRepository.delete(student);
//
//    }
//
//    @Override
//    public Student updateStudent(Long id, Student studentDetials) {
//
//        Student studentDetails = this.getStudentById(id);
//
//        studentDetails.setName(studentDetails.getName());
//        studentDetails.setEmail(studentDetails.getEmail());
//        studentDetails.setPassword(studentDetails.getPassword());
//
//        return studentRepository.save(studentDetails);
//    }
//
//    @Override
//    public Student createOrUpdateStudent(StudentDto studentDto) {
//        Student student = null;
//
//        return student;
//    }
//
//    @Override
//    public Optional<Student> getByIdOrEmail(String id, String email) {
//        return Optional.empty();
//    }

}
