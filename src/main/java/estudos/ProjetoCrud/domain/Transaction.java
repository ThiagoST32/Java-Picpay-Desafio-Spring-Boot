package estudos.ProjetoCrud.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;


import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "transactions")
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private java.lang.Long id;

    private BigDecimal valueTransacion;

    @ManyToOne
    @JoinColumn(name = "idPayeer")
    private User payeer;

    @ManyToOne
    @JoinColumn(name = "idPayee")
    private User payee;

    private LocalDateTime timeStamp;


}
