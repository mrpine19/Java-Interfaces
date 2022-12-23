package Projeto_sem_interface.model.services;

import java.time.Duration;

import Projeto_sem_interface.model.entities.CarRental;
import Projeto_sem_interface.model.entities.Invoice;
import Projeto_sem_interface.model.services.BrazilTaxesService;

public class RentalServices {

    private Double pricePerDay;
    private Double pricePerHour;

    private BrazilTaxesService brazilTaxesServicetaxService;

    public RentalServices(Double pricePerHour, Double pricePerDay, BrazilTaxesService brazilTaxesService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.brazilTaxesServicetaxService = brazilTaxesService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);
        }
        else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double tax = brazilTaxesServicetaxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
