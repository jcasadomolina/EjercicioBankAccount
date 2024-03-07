package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * @author Jose Antonio Casado Molina
 */

public class bankTest {
    BankAccount bank;

    @BeforeEach
    void setUp(){
        // Código para configurar la prueba
        bank = new BankAccount(0);
    }

    @Test
    @DisplayName("Testing withdraw a positive amount")
    void TestWithdrawPositiveAmount(){
        // Test para comprobar si es posible retirar un importe positivo de la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(0); // Crear una instancia de BankAccount con un saldo inicial de 0
        int amount = 100;
            // Act
        bank.deposit(amount);
            // Assert
        assert(bank.withdraw(amount) == true);
    }

    @Test
    @DisplayName("Testing withdraw a positive amount greater than the balance")
    void TestWithdrawAmountGreaterThanBalance(){
        // Test para comprobar si es posible retirar importe positivo mayor que el balance de la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(100); // Crear una instancia de BankAccount con un saldo inicial de 100
        int amount = 150;
            // Act
        bank.withdraw(amount);
            // Assert
        assert(bank.withdraw(amount) == false);
    }

    @Test
    @DisplayName("Testing withdraw a negative amount")
    void TestWithdrawNegativeAmount(){
        // Test para comprobar si es posible retirar importe negativo de la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(50); // Crear una instancia de BankAccount con un saldo inicial de 50
            // Assert
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(-100));
    }

    
    @Test
    @DisplayName("Testing deposit a positive amount")
    void TestDepositPositiveAmount(){
        // Test para depositar un importe en la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(0); // Crear una instancia de BankAccount con un saldo inicial de 0
        int amount = 100;
        int bal = bank.getBalance();
            // Act
        bank.deposit(amount);
            // Assert
        assert(bank.getBalance() > bal);
    }

    @Test
    @DisplayName("Testing deposit a negative amount")
    void TestDepositNegativeAmount(){
        // Test para depositar un importe negativo en la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(30); // Crear una instancia de BankAccount con un saldo inicial de 30
        int amount = -100;
            // Assert
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(amount));
    }

    
    @Test
    @DisplayName("Testing getting balance")
    void TestGetBalance(){
        // Test para obtener el balance de la cuenta bancaria

            // Arrange
        BankAccount bank = new BankAccount(0); // Crear una instancia de BankAccount con un saldo inicial de 10000
        int amount = 100;
            // Act
        bank.deposit(amount);
            // Assert
        assertEquals(bank.getBalance(), amount);
    }

    @Test
    @DisplayName("Testing calculate payment correctly")
    void TestPayment(){
        // Test para obtener correctamente el pago de la carga

            // Arrange
        BankAccount bank = new BankAccount(10000); // Crear una instancia de BankAccount con un saldo inicial de 10000
        double total_amount = 10000;
        double interes = 0.001;
        int months = 12;
        double expectedPayment = total_amount * (interes * Math.pow((1+interes), months)/(Math.pow((1+interes), months)-1)); // Valor esperado

            // Act
                // Calcular el pago
        double actualPayment = bank.payment(total_amount, interes, months);

            // Assert
                // Comprobar si los resultados son iguales con un margen de error de 0.001
        assertEquals(expectedPayment, actualPayment, 0.001);
    }

    @Test
    @DisplayName("Testing calculate the payment by entering a negative amount")
    void TestPaymentNegativeTotalAmount(){
        // Test para comprobar si al introducir un importe total negativo salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10000); // Crear una instancia de BankAccount con un saldo inicial de 10000
        double total_amount = -10000;
        double interes = 0.001;
        int months = 12;

            // Assert
        assertThrows(IllegalArgumentException.class, () -> bank.payment(total_amount, interes, months));
    }

    @Test
    @DisplayName("Testing calculate the payment by entering a negative interest")
    void TestPaymentNegativeInterest(){
        // Test para comprobar si al introducir un interes negativo salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10000); // Crear una instancia de BankAccount con un saldo inicial de 10000
        double total_amount = 10000;
        double interes = -0.001;
        int months = 12;

            // Assert
        assertThrows(IllegalArgumentException.class, () -> bank.payment(total_amount, interes, months));
    }
    
    @Test
    @DisplayName("Testing calculate the payment by entering an incorrectly month")
    void TestPaymentIncorrectlyMonthPartOne(){
        // Test para comprobar si al introducir mes incorrecto (mayor de 12) salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10000); // Crear una instancia de BankAccount con un saldo inicial de 10000
        double total_amount = 10000;
        double interes = 0.001;
        int months = 13;

            // Assert
        assertThrows(IllegalArgumentException.class, () -> bank.payment(total_amount, interes, months));
    }

    @Test
    @DisplayName("Testing calculate the payment by entering an incorrectly month")
    void TestPaymentIncorrectlyMonthPartTwo(){
        // Test para comprobar si al introducir mes incorrecto (menor de 1 de 12) salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10000); // Crear una instancia de BankAccount con un saldo inicial de 10000
        double total_amount = 10000;
        double interes = 0.001;
        int months = -4;

            // Assert
                // Comprobar si 
        assertThrows(IllegalArgumentException.class, () -> bank.payment(total_amount, interes, months));
    }

    @Test
    @DisplayName("Testing calculate pending")
    void TestPending(){
        // Test para obtener correctamente el pago pendiente de la cuenta

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = 10000.0;
        double inte = 0.001; // 1%
        int npayments = 12;
        int month = 2; // Mes específico
        double res;

            // Act
            double ant = bank.pending(amount, inte, npayments, month-1);
            res = ant - (bank.payment(amount,inte,npayments) - inte * ant);

            // Assert
                // Comprobamos si los resultados son iguales con un margen de error de 0.001
        assertEquals(res, bank.pending(amount, inte, npayments, month), 0.001);
    }

    @Test
    @DisplayName("Testing calculate the pending by entering a negative amount")
    void TestPendingNegativeAmount(){
        // Test para comprobar si al introducir un importe total negativo salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = -10000.0;
        double inte = 0.001; // 1%
        int npayments = 12;
        int month = 2; // Mes específico

            // Assert
            assertThrows(IllegalArgumentException.class, () -> bank.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("Testing calculate the pending by entering a negative interest")
    void TestPendingNegativeInterest(){
        // Test para comprobar si al introducir un interes negativo salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = 10000.0;
        double inte = -0.001; // 1%
        int npayments = 12;
        int month = 2; // Mes específico

            // Assert
            assertThrows(IllegalArgumentException.class, () -> bank.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("Testing calculate the pending the pending by entering a negative number of payments")
    void TestPendingNegativeNumberOfPayments(){
        // Test para comprobar si al introducir un numero de pagos incorrecto salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = 10000.0;
        double inte = 0.001; // 1%
        int npayments = -12;
        int month = 2; // Mes específico

            // Assert
            assertThrows(IllegalArgumentException.class, () -> bank.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("Testing calculate the pending by entering an incorrectly month")
    void TestPendingIncorrectlyMonthPartOne(){
        // Test para comprobar si al introducir mes incorrecto (mayor de 12) salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = 10000.0;
        double inte = 0.001; // 1%
        int npayments = 12;
        int month = 13; // Mes específico

            // Assert
            assertThrows(IllegalArgumentException.class, () -> bank.pending(amount, inte, npayments, month));
    }

    @Test
    @DisplayName("Testing calculate the pending by entering an incorrectly month")
    void TestPendingIncorrectlyMonthPartTwo(){
        // Test para comprobar si al introducir mes incorrecto (menor de 1 de 12) salta la excepción correspondiente

            // Arrange
        BankAccount bank = new BankAccount(10); // Crear una instancia de BankAccount con un saldo inicial de 10
        double amount = 10000.0;
        double inte = 0.001; // 1%
        int npayments = 12;
        int month = -2; // Mes específico

            // Assert
            assertThrows(IllegalArgumentException.class, () -> bank.pending(amount, inte, npayments, month));
    }

}
