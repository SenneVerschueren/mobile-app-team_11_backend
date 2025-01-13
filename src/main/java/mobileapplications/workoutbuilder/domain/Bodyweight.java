package mobileapplications.workoutbuilder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Bodyweight() {}

    public Bodyweight(Double bodyWeight, Date date) {
        this.bodyWeight = bodyWeight;
        this.date = date;
    }
}
