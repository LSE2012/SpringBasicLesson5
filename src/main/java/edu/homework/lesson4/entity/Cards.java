package edu.homework.lesson4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cards", schema = "public", catalog = "lesson4_db")
public class Cards {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cardid")
    private int cardId;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "currencycode")
    private Integer currencycode;
    @Basic
    @Column(name = "userid")
    private Integer userid;

}
