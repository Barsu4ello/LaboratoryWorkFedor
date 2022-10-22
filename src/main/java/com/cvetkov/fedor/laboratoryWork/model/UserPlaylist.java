package com.cvetkov.fedor.laboratoryWork.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    @NotBlank(message = "Title is mandatory")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    @NotNull(message = "User is mandatory")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "UserPlaylist{" +
                "id=" + id +
                ", username='" + title + '\'' +
                ", password='" + description + '\'' +
                ", user=" + user.getId() +
                '}';
    }
}
