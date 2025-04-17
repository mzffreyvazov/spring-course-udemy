package org.example.crudjpa;

import org.example.crudjpa.entity.Student;
import org.example.crudjpa.repository.StudentDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudJpaApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            createStudent(studentDAO);
            // readStudent(studentDAO);
            // findAllStudents(studentDAO);
            // deleteById(studentDAO);
        };
    }

    private void deleteById(StudentDAO studentDAO) {
        int id = 2;
        studentDAO.delete(id);
        System.out.println("Student with id " + id + " deleted.");

    }

    private void findAllStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findALl();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDAO studentDAO) {
        System.out.println("Creating student");
        Student student = new Student("Elemdar", "Polad", "Polad@gmail.com");

        System.out.println("Saving student");
        studentDAO.save(student);

        System.out.println("Saved student. Generated ID: " + student.getId());


        System.out.println("Reading student. with ID: " + student.getId());
        studentDAO.findById(student.getId());


    }

    private void createStudent(StudentDAO studentDAO) {
        System.out.println("Creating student");
        Student student = new Student("Muzeffer", "Eyvazov", "Muzeffer@gmail.com");

        System.out.println("Saving student");
        studentDAO.save(student);

        System.out.println("Saved student. Generated ID: " + student.getId());

    }
}
