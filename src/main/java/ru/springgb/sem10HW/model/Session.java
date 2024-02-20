package ru.springgb.sem10HW.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @JoinColumn(name = "useeName")
    Long id;

    @Column(name = "session_name")
    private String name;


    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "session")
    private User user;

    public Session(String name) {
        this.name = name;
    }

    public Session(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
