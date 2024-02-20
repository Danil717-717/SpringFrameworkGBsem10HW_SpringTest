package ru.springgb.sem10HW.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
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
    //@JoinColumn(name = "user_id")
    @JoinTable(
            name = "user_session",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    private Session session;


    public void addSession(Session session) {
        this.session = session;

    }

//    public void removeTask(long taskId) {
//        Task task = this.tasks.stream().filter(t -> t.getId() == taskId)
//                .findFirst().orElse(null);
//        if (task != null) {
//            this.tasks.remove(task);
//
//        }
//    }
}
