package bank;

public class BankAccount {
    private int balance = 0;

    // Crear Cuenta Bancaria (con un balance inicial)
    public BankAccount(int startingBalance) {
        this.balance = startingBalance;
    }
    // Retirar importe 
    public boolean withdraw(int amount) {
        if (amount <0) throw new IllegalArgumentException("Amount cannot be negative");
        if(balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    // Depositar importe
    public int deposit(int amount) {
        if (amount <0) throw new IllegalArgumentException("Amount cannot be negative");
        balance += amount;
        return balance;
    }
    // Obtener balance
    public int getBalance() {
        return balance;
    }
    // Calculate the payment per month for a loan
    public double payment(double total_amount, double interest, int npayments){
        if (total_amount <0) throw new IllegalArgumentException("Total amount cannot be negative");
        else if (interest <0) throw new IllegalArgumentException("Interest cannot be negative");
        else if (npayments <0) throw new IllegalArgumentException("Number of payments cannot be negative");
        else if (npayments > 12) throw new IllegalArgumentException("Number of payments cannot be negative");
        return total_amount * (interest * Math.pow((1+interest), npayments)/(Math.pow((1+interest), npayments)-1));
    }

    // Calculate the pending amount for a loan in a month
    public double pending (double amount, double inte, int npayments, int month){
        if (amount <0) throw new IllegalArgumentException("Amount cannot be negative");
        else if (inte <0) throw new IllegalArgumentException("Interest cannot be negative");
        else if (npayments <0) throw new IllegalArgumentException("Number of payments cannot be greater than 12");
        double res;
        if(month==0){
            res=amount;
        } else if (month < 1 || month > 12){
            throw new IllegalArgumentException("Improperly entered month");
        } else{
            double ant=pending(amount, inte, npayments, month-1);
            res = ant - (payment(amount,inte,npayments) - inte * ant);
        }
        return res;
    }
}
