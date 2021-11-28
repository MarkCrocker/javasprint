package com.sprint.s4.java;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
		return args -> {
			Student mark = new Student(
					"Mark",
					"Crocker",
					"mark.crocker@keyin.com",
					38
			);

			Student john = new Student(
					"John",
					"Doe",
					"john.doe@email.com",
					65
			);

			System.out.println("Adding mark and john");
			studentRepository.saveAll(List.of(mark, john));

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());

			studentRepository
					.findById(2L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with ID 2 not found"));

			studentRepository
					.findById(3L)
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with ID 3 not found"));

			System.out.println("Select all students");
			List<Student> students = studentRepository.findAll();
			students.forEach(System.out::println);

			System.out.println("Delete mark");
			studentRepository.deleteById(1L);

			System.out.print("Number of students: ");
			System.out.println(studentRepository.count());
		}
	}

}
