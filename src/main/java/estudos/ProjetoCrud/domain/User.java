package estudos.ProjetoCrud.domain;


import estudos.ProjetoCrud.dto.UserDTO;
import estudos.ProjetoCrud.dto.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private java.lang.Long id;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String document;

    private BigDecimal tokens;

    @Enumerated(EnumType.STRING)
    private UserType userType;



    public User(UserDTO data) {
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.document = data.document();
        this.tokens = data.tokens();
        this.userType = data.userType();
    }

    public User(UserUpdateDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.email = data.email();
        this.userType = data.userType();
    }
}


