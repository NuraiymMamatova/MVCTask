package peaksoft.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.CascadeType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "task_seq", sequenceName = "task_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    private Long id;

    @NotNull(message = "name cannot be null")
    @Column(name = "task_name")
    private String taskName;

    @NotNull(message = "task text cannot be null")
    @Column(name = "task_text")
    private String taskText;

    @NotNull(message = "deadline cannot be null")
    private Date deadline;

    @ManyToOne(cascade = { MERGE, REFRESH, DETACH, PERSIST}, fetch = FetchType.EAGER)
    private Lesson lesson;

    public Task(String taskName, String taskText, Date deadline) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadline = deadline;
    }
}
