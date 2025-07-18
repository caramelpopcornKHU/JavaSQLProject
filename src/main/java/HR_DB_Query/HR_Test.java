package HR_DB_Query;  // ← 파일 위치에 맞게 추가

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HR_Test {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/newhr?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    public static void main(String[] args) {
        String firstName = "Steven";   // 예시 입력값
        String lastName = "King";      // 예시 입력값

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM employees WHERE first_name = ? AND last_name = ?")) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String lastNameResult = rs.getString("last_name");
                    System.out.println("사원 성: " + lastNameResult);
                }
            }

        } catch (SQLException e) {
            System.err.println("DB 연결 또는 쿼리 실행 중 오류 발생: " + e.getMessage());
        }
    }
}
