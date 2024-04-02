package estudos.ProjetoCrud.dto;

import estudos.ProjetoCrud.domain.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String email, String document, BigDecimal tokens, UserType userType) {
}
