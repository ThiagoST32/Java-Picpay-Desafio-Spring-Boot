package estudos.ProjetoCrud.repositories;

import estudos.ProjetoCrud.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepositories extends JpaRepository<Transaction, Long> {

    Optional<Transaction>findById(Long id);
}
