import java.sql.*;
import java.util.*;

public class OnlineBanking {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/online_banking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // Deposit method
    public static void deposit(float amount) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updateQuery = "UPDATE account SET balance = balance + ? WHERE account_id = 1";
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setFloat(1, amount);
            pstmt.executeUpdate();

            System.out.println("Credited Rs." + amount + "/- to your account.");
            checkBalance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Withdrawal method
    public static void withdrawal(float amount) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String checkBalanceQuery = "SELECT balance FROM account WHERE account_id = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(checkBalanceQuery);

            if (rs.next()) {
                float balance = rs.getFloat("balance");

                if (balance < amount) {
                    System.out.println("Insufficient Balance!!!");
                } else {
                    String updateQuery = "UPDATE account SET balance = balance - ? WHERE account_id = 1";
                    PreparedStatement pstmt = conn.prepareStatement(updateQuery);
                    pstmt.setFloat(1, amount);
                    pstmt.executeUpdate();

                    System.out.println("Debited Rs." + amount + "/- from your account.");
                    checkBalance();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check Balance method
    public static void checkBalance() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT balance FROM account WHERE account_id = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                float balance = rs.getFloat("balance");
                System.out.println("Bank Balance fetched successfully!");
                System.out.println("Rs." + balance + "/-");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Banking Operations:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdrawal");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");

        while (true) {
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the amount to deposit: ");
                    float depositAmount = sc.nextFloat();
                    deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    float withdrawAmount = sc.nextFloat();
                    withdrawal(withdrawAmount);
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("Thank You!!!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice!!!");
            }
        }
    }
}

