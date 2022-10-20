package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_playlists")
@Setter
@Getter
@NoArgsConstructor
public class UserPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String username;

    @Column(name = "description")
    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @Override
    public String toString() {
        return "UserPlaylist{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
