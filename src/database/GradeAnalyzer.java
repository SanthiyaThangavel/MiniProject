package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface GradeAnalyzer {
    void enterStudentMarks(Connection connection, Scanner scanner) throws SQLException;

    void editStudentMarks(Connection connection, Scanner scanner) throws SQLException;

    void calculateAverageMarksBySubject(Connection connection) throws SQLException;

    void calculateOverallAverage(Connection connection) throws SQLException;
}