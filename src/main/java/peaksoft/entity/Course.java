package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq",sequenceName = "course_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    private Long id;

    @Column(name = "course_name")
    private String courseName;

    private Integer duration;

    private String description;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH}, fetch = LAZY)
    private Company company;
//
//    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
//    private List<Lesson> lessons;
//
//    @ManyToMany(cascade = ALL, fetch = LAZY, mappedBy = "courses")
//    private List<Group> groups;
//
//    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "course")
//    private List<Instructor> instructors;

    public Course(String courseName, int duration, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.description = description;
    }
}
