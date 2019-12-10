package com.kaebit.boardbackend.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "boardComment")
@Data
@NoArgsConstructor
public class BoardComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BoardComment(Board board, String author, String content) {
        this.board = board;
        this.author = author;
        this.content = content;
    }
}
