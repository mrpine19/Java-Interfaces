package Exercicio_fixacao.application;

import Exercicio_fixacao.model.entities.Contract;
import Exercicio_fixacao.model.entities.Installment;
import Exercicio_fixacao.model.service.ContractService;
import Exercicio_fixacao.model.service.PaypalService;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Entre os dados do contrato:");
        System.out.print("Número: ");
        int number = sc.nextInt();
        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.next(), dtf);
        System.out.print("Valor do contrato: ");
        double totalValue = sc.nextDouble();
        System.out.print("Entre com o número de parcelas: ");
        int n = sc.nextInt();

        Contract contract = new Contract(number, date, totalValue);
        ContractService service = new ContractService(new PaypalService());
        service.processContract(contract, n);

        System.out.println();

        System.out.println("Parcelas: ");
        for (Installment installment : contract.getInstallments()){
            System.out.println(installment);
        }

        sc.close();
    }
}
