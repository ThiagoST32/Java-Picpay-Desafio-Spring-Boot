package estudos.ProjetoCrud.dto;

import java.math.BigDecimal;


public record TransactionDTO(BigDecimal valueTransaction, Long idPayeer, Long idPayee) {

}
