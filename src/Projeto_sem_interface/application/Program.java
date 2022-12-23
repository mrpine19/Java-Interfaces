package Projeto_sem_interface.application;

import Projeto_sem_interface.model.entities.CarRental;
import Projeto_sem_interface.model.entities.Vehicle;
import Projeto_sem_interface.model.services.BrazilTaxesService;
import Projeto_sem_interface.model.services.RentalServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel: ");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalServices rentalServices = new RentalServices(pricePerHour, pricePerDay, new BrazilTaxesService());

        rentalServices.processInvoice(cr);

        System.out.println("Fatura:");
        double pagamento = cr.getInvoice().getBasicPayment();
        System.out.println("Pagamento básico: "+pagamento);
        System.out.println("Imposto: "+cr.getInvoice().getTax());
        System.out.println("Pagamento total: "+cr.getInvoice().getTotalPayment());


        sc.close();
    }
}
