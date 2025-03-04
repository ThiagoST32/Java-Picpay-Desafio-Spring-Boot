package estudos.ProjetoCrud.repositories;

import estudos.ProjetoCrud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {


//    Optional<User> findByUserByDocument(String document);
    Optional<User> findById(Long id);
}
