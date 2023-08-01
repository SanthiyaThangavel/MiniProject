package database;

import java.sql.*;
import java.util.Scanner;

public abstract class AbstractGradeAnalyzer implements GradeAnalyzer {
    protected Connection connection;

    public AbstractGradeAnalyzer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public abstract void enterStudentMarks(Connection connection, Scanner scanner) throws SQLException;

    @Override
    public abstract void editStudentMarks(Connection connection, Scanner scanner) throws SQLException;

    @Override
    public void calculateOverallAverage(Connection connection) throws SQLException {
        String query = "SELECT AVG(marks) AS overall_average FROM mentor";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            double overallAverage = resultSet.getDouble("overall_average");
            System.out.println("\nOverall Average Grade: " + overallAverage);
        } else {
            System.out.println("No records found in the exam table.");
        }

        resultSet.close();
        statement.close();
    }

    @Override
    public void calculateAverageMarksBySubject(Connection connection) throws SQLException {
        String query = "SELECT subjects, AVG(marks) AS average_mark FROM mentor GROUP BY subjects";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\nSubject\t\tAverage Grade");
        while (resultSet.next()) {
            String subjects = resultSet.getString("subjects");
            double averageMark = resultSet.getDouble("average_mark");
            System.out.printf("%s\t\t%.2f\n", subjects, averageMark);
        }

        resultSet.close();
        statement.close(); 
    }
}
