package estudos.ProjetoCrud.controlllers;
import estudos.ProjetoCrud.domain.Transaction;
import estudos.ProjetoCrud.dto.TransactionDTO;
import estudos.ProjetoCrud.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {


    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction newTransaction = transactionService.transferTokens(transactionDTO);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>>getAllTransactions(){
        List<Transaction> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getOneTransaction(@PathVariable("id") Long id) throws Exception{
        Transaction transactions = transactionService.getOneTransaction(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

}
