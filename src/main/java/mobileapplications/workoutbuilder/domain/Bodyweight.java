package mobileapplications.workoutbuilder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bodyweight")
public class Bodyweight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double bodyWeight;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Bodyweight() {}

    public Bodyweight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }
}
