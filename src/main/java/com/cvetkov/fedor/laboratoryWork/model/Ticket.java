package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
@Setter
@Getter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "concert_id")
    Concert concert;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", concert=" + concert +
                ", user=" + user.getId() +
                '}';
    }
}
