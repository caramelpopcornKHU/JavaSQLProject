package HR_DB_Query; // ← 파일 위치에 맞게 추가

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HR_Test {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/newhr?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "rootroot";

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String switch_1 = "a"; // 입력스위치

		while (switch_1 != "Q") { // Q를 누르면 종료하는 기능
			System.out.println("안녕하세요 한국국토정보공사 인사정보시스템입니다.(종료 : Q)");
			System.out.println("사용할 수 있는 기능은 다음과 같습니다.");
			System.out.println("1. 직원이름으로 직원정보를 검색");
			System.out.println("2. 입사년도로 직원정보를 검색");
			System.out.println("3. 부서번호로 직원정보를 검색");
			System.out.println("4. 직무로 직원정보를 검색");
			System.out.println("5. 도시이름으로 직원정보를 검색");
			System.out.println("6. 직원들의 통계자료를 출력");
			System.out.println("7. 부서장 성으로 그 부서의 직원정보를 검색");
			System.out.println("8. 나라 이름으로 그 나라에 근무하는 직원정보를 검색");
			switch_1 = scan.next();

			switch (switch_1) {
			case "1":
				System.out.println("1입력 했습니다.");
				break;
			case "2":
				System.out.println("2입력 했습니다.");
				break;
			case "3":
				System.out.println("3입력 했습니다.");
				break;
			case "4":
				System.out.println("4입력 했습니다.");
				break;
			case "5":
				System.out.println("5입력 했습니다.");
				break;
			case "6":
				System.out.println("6입력 했습니다.");
				break;
			case "7":
				System.out.println("7입력 했습니다.");
				break;
			case "8":
				System.out.println("8입력 했습니다.");
				break;
			case "Q" :
				System.out.println("종료합니다.");
				switch_1 = "Q";
				break;
			}

		}

		/*
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM employees WHERE first_name = ? AND last_name = ?")) {

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
		*/
		
		
	}
}
