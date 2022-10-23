package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Table(name = "subscriptions")
@Setter
@Getter
@NoArgsConstructor
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;

    @Positive(message = "Price must be greater than zero")
    @Column(name = "price")
    private Integer price;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "subscription",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<UserSubscription> userSubscriptions;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
