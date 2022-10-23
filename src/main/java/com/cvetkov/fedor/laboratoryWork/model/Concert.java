package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "concerts")
@Setter
@Getter
@NoArgsConstructor
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "title")
    private String name;

    @NotBlank(message = "Performer is mandatory")
    @Column(name = "performer")
    private String performer;

    @NotNull(message = "Performer is mandatory")
    @Column(name = "concert_date")
    private LocalDateTime concertDate;

    @NotNull(message = "City is mandatory")
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(
            mappedBy = "concert",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Ticket> tickets;

    @Override
    public String toString() {
        return "Concert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", performer='" + performer + '\'' +
                ", concertDate=" + concertDate +
                ", city=" + city.getId() +
                '}';
    }
}
