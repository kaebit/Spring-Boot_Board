package com.kaebit.boardbackend.Model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String name, String user_id, String password) {
        this.name = name;
        this.user_id = user_id;
        this.password = password;
    }
}
