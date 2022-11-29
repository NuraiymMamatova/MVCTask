package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = ("instructors"))
public class Instructor {

    @Id
    @SequenceGenerator(name = "instructor_seq", sequenceName = "instructor_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    private String specialization;

    @Column(name = "count_of_students")
    private Long count;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST}, fetch = FetchType.EAGER)
    private Course course;

    public Instructor(String firstName, String lastName, String phoneNumber, String email, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.specialization = specialization;
    }

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }

}
