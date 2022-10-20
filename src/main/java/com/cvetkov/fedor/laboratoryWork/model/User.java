package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "author")
    Author author;

    @ManyToMany
    @JoinTable(
            name = "uploaded_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id"))
    private List<Audio> audios;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<UserSubscription> userSubscriptions;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @OneToOne(mappedBy = "user")
    private UserPlaylist userPlaylist;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", author=" + author.getId() +
                '}';
    }
}
