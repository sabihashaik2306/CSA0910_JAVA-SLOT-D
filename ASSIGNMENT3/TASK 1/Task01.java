import java.util.Scanner;

public class Task01 {
    static class InvalidWithdrawalException extends Exception {
        InvalidWithdrawalException(String message) { super(message); }
    }

    static void withdraw(String account, String amountText, double balance)
            throws InvalidWithdrawalException {
        try {
            int amount = Integer.parseInt(amountText);
            if (amount <= 0) throw new InvalidWithdrawalException("Withdrawal amount must be positive.");
            if (amount > balance) throw new InvalidWithdrawalException("Insufficient balance.");
            System.out.println("Account: " + account);
            System.out.println("Withdrawal successful: Rs." + amount);
            System.out.println("Remaining balance: Rs." + (balance - amount));
        } catch (NumberFormatException e) {
            System.out.println("Input error: Withdrawal amount must be numeric.");
        } catch (InvalidWithdrawalException e) {
            System.out.println("Transaction rejected: " + e.getMessage());
        } finally {
            System.out.println("ATM session closed.");
        }
    }

    public static void main(String[] args) {
        System.out.println("ATM WITHDRAWAL TESTS");
        String[][] tests = {
            {"1001", "500"}, {"1002", "abc"}, {"1003", "-50"},
            {"1004", "2000"}, {"1005", "100"}
        };
        double[] balances = {1500, 1500, 1500, 1500, 1500};
        for (int i = 0; i < tests.length; i++) {
            System.out.println("\\nTest " + (i + 1));
            try { withdraw(tests[i][0], tests[i][1], balances[i]); }
            catch (InvalidWithdrawalException e) { System.out.println("Unexpected: " + e.getMessage()); }
        }
        System.out.println("\\nNote: missing card-reader file would be handled as IOException in a file-based implementation.");
    }
}
