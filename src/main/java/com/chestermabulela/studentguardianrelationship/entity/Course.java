package com.chestermabulela.studentguardianrelationship.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
	@Id
	@SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "course_sequence")
	private Long courseId;
    private String title;
    private Integer credit;
    
    @OneToOne(mappedBy = "course")
    private CourseMaterial courseMaterial;
    
    @jakarta.persistence.ManyToOne(
            cascade = jakarta.persistence.CascadeType.ALL
    )
    @jakarta.persistence.JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @jakarta.persistence.ManyToMany(
            cascade = jakarta.persistence.CascadeType.ALL
    )
    @jakarta.persistence.JoinTable(
            name = "studen_course_map",
            joinColumns = @jakarta.persistence.JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @jakarta.persistence.JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
	
	

}
