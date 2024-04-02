package estudos.ProjetoCrud.dto;

import estudos.ProjetoCrud.domain.UserType;

public record UserUpdateDTO(String firstName, String lastName, String email, UserType userType) {
}
