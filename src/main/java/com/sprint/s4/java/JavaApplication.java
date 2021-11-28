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

			Student mark2 = new Student(
					"Mark",
					"Crocker",
					"mark2.crocker@keyin.com",
					30
			)

			Student john = new Student(
					"John",
					"Doe",
					"john.doe@email.com",
					65
			);

			studentRepository.saveAll(List.of(mark, john, mark2));

			studentRepository
					.findStudentByEmail("john.doe@email.com")
					.ifPresentOrElse(
							System.out::println,
							() -> System.out.println("Student with email john.doe@email.com not found")
					);
			studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqual(
					"Mark",
					38
			).forEach(System.out::println);

			studentRepository.selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
					"Mark",
					38
			).forEach(System.out::println);

			System.out.println("Deleting Mark 2");
			System.out.println(studentRepository.deleteStudentById(3L));
		};
	}

}
