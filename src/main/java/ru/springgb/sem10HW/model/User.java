package ru.springgb.sem10HW.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Transient
    private String password;

    @Column(name = "hashPassword")
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;


    @OneToOne
    @JoinTable(
            name = "user_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private Session session;


    public User(Long id, String name, String lastName, String email, String hashPassword) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.hashPassword = hashPassword;
    }

    public void addSession(Session session) {
        this.session = session;

    }


}
