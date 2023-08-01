package database;

import java.sql.*;
import java.util.Scanner;

public class StudentGradeAnalyzer {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/studentgrade";
        String username = "root";
        String password = "Sree@2009";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            Scanner scanner = new Scanner(System.in);

            GradeAnalyzer gradeAnalyzer = new StudentGradeAnalyzerImpl(connection);

            while (true) {
                System.out.println("1. Enter Student Marks");
                System.out.println("2. Edit Student Marks");
                System.out.println("3. Calculate Average Marks for Each Subject");
                System.out.println("4. Calculate Overall Average of All Marks");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid choice (1-5).");
                    scanner.nextLine(); // Clear the invalid input from the scanner
                    continue;
                }

                switch (choice) { 
                    case 1:
                        gradeAnalyzer.enterStudentMarks(connection, scanner);
                        break;
                    case 2:
                        gradeAnalyzer.editStudentMarks(connection, scanner);
                        break;
                    case 3:
                        gradeAnalyzer.calculateAverageMarksBySubject(connection);
                        break;
                    case 4:
                        gradeAnalyzer.calculateOverallAverage(connection);
                        break;
                    case 5:
                        connection.close();
                        scanner.close();
                        System.out.println("Exiting the application. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
