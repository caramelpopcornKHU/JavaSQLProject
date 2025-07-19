package HR_DB_Query; // ← 파일 위치에 맞게 추가

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HR_Test {


	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);

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
				System.out.print("이름을 입력(성 혹은 이름): ");
				try {
					String name = scan2.nextLine();
					EmployeeDAO.findByName(name);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
				
			case "2":
				System.out.println("2입력 했습니다.");
				System.out.print("입사년도 입력: ");
				try {
					String yearString = scan2.nextLine();
					int year = Integer.parseInt(yearString);
					EmployeeDAO.findByHireYear(year);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case "3":
				System.out.println("3입력 했습니다.");
				System.out.print("부서번호 입력: ");
				try {
					String deptIDString = scan2.nextLine();
					int deptID = Integer.parseInt(deptIDString);
					EmployeeDAO.findByDeptId(deptID);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "4":
				System.out.println("4입력 했습니다.");
				System.out.println("직무는 다음과 같습니다.\n" + "AC_ACCOUNT  AC_MGR     AD_ASST    AD_PRES\n"
						+ "AD_VP       FI_ACCOUNT FI_MGR     HR_REP\n" + "IT_PROG     MK_MAN     MK_REP     PR_REP\n"
						+ "PU_CLERK    PU_MAN     SA_MAN     SA_REP\n" + "SH_CLERK    ST_CLERK   ST_MAN");

				System.out.print("직무를 입력:");
				try {
					String job = scan2.nextLine();
					EmployeeDAO.getEmpListByJobId(job);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
			case "Q":
				System.out.println("종료합니다.");
				switch_1 = "Q";
				break;
			}

		}

		

	}
}
