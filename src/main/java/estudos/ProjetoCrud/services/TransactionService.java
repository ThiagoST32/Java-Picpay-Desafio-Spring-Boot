package estudos.ProjetoCrud.services;

import estudos.ProjetoCrud.domain.Transaction;
import estudos.ProjetoCrud.domain.User;
import estudos.ProjetoCrud.dto.TransactionDTO;
import estudos.ProjetoCrud.repositories.TransactionRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepositories transactionRepositories;

    @Autowired
    UserService userService;


    public Transaction transferTokens(TransactionDTO transaction) throws Exception {
        User payeer = this.userService.findByIdUser(transaction.idPayeer());
        userService.verifyTransaction(payeer, transaction.valueTransaction());

        boolean isAuthorized = this.authorizedTransaction(payeer, transaction.valueTransaction());
        if (!isAuthorized) {
            throw new Exception("Transaction no accept!!!");
        }
        return completeTransaction(transaction, payeer);
    }

    public Transaction completeTransaction(TransactionDTO transaction, User payeer) throws Exception {
        User payee = this.userService.findByIdUser(transaction.idPayee());

        Transaction newTransaction = new Transaction();
        newTransaction.setValueTransacion(transaction.valueTransaction());
        newTransaction.setPayeer(payeer);
        newTransaction.setPayee(payee);
        newTransaction.setTimeStamp(LocalDateTime.now());
        this.transactionRepositories.save(newTransaction);

        payeer.setTokens(payeer.getTokens().subtract(transaction.valueTransaction()));
        payee.setTokens(payee.getTokens().add(transaction.valueTransaction()));

        this.userService.saveUser(payee);
        this.userService.saveUser(payeer);

        return newTransaction;
    }

    public boolean authorizedTransaction(User sender, BigDecimal valueTokens) throws Exception {
        if (sender.getTokens().compareTo(valueTokens) <= 0) {
            throw new Exception("Balance insufficient for transaction!!");
        }
        return true;
    }

    public List<Transaction> getAllTransactions() {
        System.out.println(this.transactionRepositories.findAll());
        return this.transactionRepositories.findAll();
    }

    public Transaction getOneTransaction(Long id) {
        return transactionRepositories.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found!!"));
    }

}
