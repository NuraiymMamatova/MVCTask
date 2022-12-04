package peaksoft.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @SequenceGenerator(name = "company_seq",sequenceName = "company_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "company_name")
    private String companyName;

    @NotNull(message = "Located country cannot be null")
    @Column(name = "located_country")
    private String locatedCountry;

    @Column(name = "count_of_students")
    private Long count = 0L;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private List<Course> courses;

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);

    }

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }
}
