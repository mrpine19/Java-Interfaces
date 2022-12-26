package Exercicio_fixacao.model.service;

import Exercicio_fixacao.model.entities.Contract;
import Exercicio_fixacao.model.entities.Installment;

import java.time.LocalDate;
import java.util.Date;

public class ContractService {
    OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months){
        double basicQuota = contract.getTotalValue()/months;
        for (int i = 1; i<=months; i++){
            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interest = onlinePaymentService.interest(basicQuota, i);
            double paymentFee = onlinePaymentService.paymentFee(interest);
            contract.getInstallments().add(new Installment(dueDate, paymentFee));
        }
    }
}
