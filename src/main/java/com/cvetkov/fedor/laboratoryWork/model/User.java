package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "author")
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "uploaded_by_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "audio_id"))
    private List<Audio> audios;

    @OneToOne(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private UserSubscription userSubscription;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<UserPlaylist> userPlaylists;


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
