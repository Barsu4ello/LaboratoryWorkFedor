package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_subscriptions")
@Setter
@Getter
@NoArgsConstructor
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "IsValid is mandatory")
    @Column(name = "is_valid")
    private boolean isValid;

    @NotNull(message = "Subscription is mandatory")
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @NotNull(message = "User is mandatory")
    @OneToOne
    @JoinColumn(name = "host_user_id")
    private User user;

    @Override
    public String toString() {
        return "UserSubscription{" +
                "id=" + id +
                ", isValid=" + isValid +
                ", subscription=" + subscription.getId() +
                ", user=" + user.getId() +
                '}';
    }
}
