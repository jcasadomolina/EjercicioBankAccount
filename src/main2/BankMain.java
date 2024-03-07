package bank;

public class BankMain {

    public static void main(String args[]){
        // Crea nueva cuenta bancaria con balance inicial = 10
        BankAccount bank =new BankAccount(10);
        // El importe total de la cuenta será de 10.000
        double total_amount =10000; 
        // El interés será 0,001
        double interes = 0.001;
        // Los meses serán 12
        int months = 12;

        System.out.printf("Load payment of amount %f, with interes %f and in %d months is: %f\n", total_amount, 
            interes, months, bank.payment(total_amount, interes, months));

        System.out.printf("Load pending payment of amount %f, with interes %f, %d months, in month %d is: %f\n", total_amount, 
            interes, months, 2, bank.pending(total_amount, interes, months, 2));
    }

    
}
