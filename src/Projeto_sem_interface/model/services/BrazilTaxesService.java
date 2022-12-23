package Projeto_sem_interface.model.services;

public class BrazilTaxesService {
    public double tax(double amount){
        if(amount <= 100){
            return amount * 0.20;
        }
        else{
            return amount * 0.15;
        }
    }
}
