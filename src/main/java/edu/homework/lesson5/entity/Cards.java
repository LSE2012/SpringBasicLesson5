package edu.homework.lesson5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Data
@Table(name = "cards", schema = "public", catalog = "lesson4_db")
public class Cards {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NonNull
    @Column(name = "cardid")
    private int cardId;

    @NonNull
    @Size(min = 8 , max = 10 )
    @Column(name = "number")
    private String number;

    @Column(name = "currencycode")
    private Integer currencycode;

    @NonNull
    @Column(name = "userid")
    private Integer userid;

    public Cards() {

    }
}
