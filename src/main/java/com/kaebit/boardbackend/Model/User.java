package com.kaebit.boardbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pk")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String pk;

    @Column(name = "name")
    private String name;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String name, String id, String password) {
        this.name = name;
        this.id = id;
        this.password = password;
    }
}
