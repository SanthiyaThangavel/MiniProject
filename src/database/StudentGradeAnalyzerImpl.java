package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentGradeAnalyzerImpl extends AbstractGradeAnalyzer {
    private encapsulation mentorInfo;

    public StudentGradeAnalyzerImpl(Connection connection) {
        super(connection);
        mentorInfo = new encapsulation();
    }

    @Override
    public void enterStudentMarks(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Student Id: ");
        int studentid = scanner.nextInt();
        System.out.print("Enter Student Name: ");
        String studentname = scanner.next();
        System.out.print("Enter Mentor Id: ");
        int mentorid = scanner.nextInt();
        System.out.print("Enter Subject: ");
        String subjects = scanner.next();
        System.out.print("Enter Mark: ");
        int marks = scanner.nextInt();

        String insertQuery = "INSERT INTO mentor (studentid, studentname, mentorid, subjects, marks) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, studentid);
        preparedStatement.setString(2, studentname);
        preparedStatement.setInt(3, mentorid);
        preparedStatement.setString(4, subjects);
        preparedStatement.setInt(5, marks);
        preparedStatement.executeUpdate();

        System.out.println("Marks added successfully!");
    }

    @Override
    public void editStudentMarks(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Student Id: ");
        int studentid = scanner.nextInt();
        System.out.print("Enter Student Name: ");
        String studentname = scanner.next();
        System.out.print("Enter Mentor Id: ");
        int mentorid = scanner.nextInt();
        System.out.print("Enter Subject: ");
        String subjects = scanner.next();
        System.out.print("Enter New Mark: ");
        int marks = scanner.nextInt();

        String updateQuery = "UPDATE mentor SET marks = ? WHERE studentid = ? AND studentname = ? AND mentorid = ? AND subjects = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1, marks);
        preparedStatement.setInt(2, studentid);
        preparedStatement.setString(3, studentname);
        preparedStatement.setInt(4, mentorid);
        preparedStatement.setString(5, subjects);
        int rowsUpdated = preparedStatement.executeUpdate();

        if (rowsUpdated >= 0) {
            System.out.println("Marks updated successfully!");
        } else {
            System.out.println("No matching records found. Please check the student name and subject.");
        }
    }
}
