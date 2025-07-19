package HR_DB_Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAO {


	// 1. 직원 이름으로 직원 정보 검색
	// first_name 또는 last_name에 입력된 키워드가 포함된 모든 레코드 조회
	public static List<Employee> findByName(String name) {
		List<Employee> result = new ArrayList<Employee>();

		String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			// 와일드카드로 성 또는 이름 찾기
			String kw = "%" + name + "%";
			ps.setString(1, kw);
			ps.setString(2, kw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.employee_id = rs.getInt("employee_id");
				emp.first_name = rs.getString("first_name");
				emp.last_name = rs.getString("last_name");
				emp.email = rs.getString("email");
				emp.phone_number = rs.getString("Phone_number");
				emp.hire_date = rs.getTimestamp("hire_date");
				emp.job_id = rs.getString("job_id");
				emp.salary = rs.getDouble("salary");
				emp.commission_pct = rs.getDouble("commission_pct");
				emp.manager_id = rs.getInt("manager_id");
				emp.department_id = rs.getInt("department_id");
				result.add(emp);
			}
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	// 2. 입사년도별로 직원 검색
	// hire_date 컬럼의 연도가 입력된 연도와 일치하는 레코드 조회
	public static List<Employee> findByHireYear(int year) {
		List<Employee> result = new ArrayList<Employee>();
		String sql = "SELECT * FROM employees WHERE YEAR(hire_date) = ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, year);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.employee_id = rs.getInt("employee_id");
				emp.first_name = rs.getString("first_name");
				emp.last_name = rs.getString("last_name");
				emp.email = rs.getString("email");
				emp.phone_number = rs.getString("Phone_number");
				emp.hire_date = rs.getTimestamp("hire_date");
				emp.job_id = rs.getString("job_id");
				emp.salary = rs.getDouble("salary");
				emp.commission_pct = rs.getDouble("commission_pct");
				emp.manager_id = rs.getInt("manager_id");
				emp.department_id = rs.getInt("department_id");
				result.add(emp);
			}
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	// 3. 부서번호별로 직원 검색
	// department_id 컬럼이 입력된 부서번호와 일치하는 레코드 조회
	public static List<Employee> findByDeptId(int deptId) {
		List<Employee> result = new ArrayList<Employee>();

		String sql = "SELECT * FROM employees WHERE department_id = ?";
		try (Connection conn = DbUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, deptId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.employee_id = rs.getInt("employee_id");
				emp.first_name = rs.getString("first_name");
				emp.last_name = rs.getString("last_name");
				emp.email = rs.getString("email");
				emp.phone_number = rs.getString("Phone_number");
				emp.hire_date = rs.getTimestamp("hire_date");
				emp.job_id = rs.getString("job_id");
				emp.salary = rs.getDouble("salary");
				emp.commission_pct = rs.getDouble("commission_pct");
				emp.manager_id = rs.getInt("manager_id");
				emp.department_id = rs.getInt("department_id");
				result.add(emp);
			}
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}


	// 4번 : 직무로 검색 메서드
	public static List<Employee> getEmpListByJobId(String jobId) throws Exception {
		List<Employee> result = new ArrayList<Employee>();

		// db.prooerties 파일을 개인적으로 만들어서 사용한다.
		Connection conn = DbUtil.getConnection();

		String sql = "select * from employees where job_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, jobId);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.employee_id = rs.getInt("employee_id");
			emp.first_name = rs.getString("first_name");
			emp.last_name = rs.getString("last_name");
			emp.email = rs.getString("email");
			emp.phone_number = rs.getString("Phone_number");
			emp.hire_date = rs.getTimestamp("hire_date");
			emp.job_id = rs.getString("job_id");
			emp.salary = rs.getDouble("salary");
			emp.commission_pct = rs.getDouble("commission_pct");
			emp.manager_id = rs.getInt("manager_id");
			emp.department_id = rs.getInt("department_id");
			result.add(emp);
		}
		if (result.size() == 0) {
			System.out.println("\n입력하신 \"" + jobId + "\"라는 직업명은 존재하지 않습니다.");
			System.out.println("처음으로 돌아갑니다.\n");
		} else {
			System.out.println("\n존재하는 직원은 총 " + result.size() + "명 입니다.");
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
			System.out.println("\n");
		}
		return result;
	}

	
	// 5번 도시로 검색
	
	public static List<Employee> getEmpListByCityName(String cityName) throws Exception {
		List<Employee> result = new ArrayList<Employee>();

		Connection conn = DbUtil.getConnection();

		String sql = "select e.*, l.city  from employees e left join departments d using(department_id) "
				+ "left join locations l using(location_id) where l.city = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, cityName);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.employee_id = rs.getInt("employee_id");
			emp.first_name = rs.getString("first_name");
			emp.last_name = rs.getString("last_name");
			emp.email = rs.getString("email");
			emp.phone_number = rs.getString("Phone_number");
			emp.hire_date = rs.getTimestamp("hire_date");
			emp.job_id = rs.getString("job_id");
			emp.salary = rs.getDouble("salary");
			emp.commission_pct = rs.getDouble("commission_pct");
			emp.manager_id = rs.getInt("manager_id");
			emp.department_id = rs.getInt("department_id");
			emp.city = rs.getString("city");
			result.add(emp);
		}
		if (result.size() == 0) {
			System.out.println("\n입력하신 \"" + cityName + "\"에는 직원이 존재하지 않습니다.");
			System.out.println("처음으로 돌아갑니다.\n");
		} else {
			System.out.println("\n존재하는 직원은 총 " + result.size() + "명 입니다.");
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
			System.out.println("\n");
		}
		return result;
	}

	
	
	// 7번 부서장 이름으로 검색
	
	public static List<Employee> getEmpListByManagerName(String managerName) throws Exception {
		List<Employee> result = new ArrayList<Employee>();

		Connection conn = DbUtil.getConnection();

		String sql = "select * from employees where department_id "
				+ "in (select department_id from departments where manager_id "
				+ "in (select employee_id from employees where last_name = ?))";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, managerName);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Employee emp = new Employee();
			emp.employee_id = rs.getInt("employee_id");
			emp.first_name = rs.getString("first_name");
			emp.last_name = rs.getString("last_name");
			emp.email = rs.getString("email");
			emp.phone_number = rs.getString("Phone_number");
			emp.hire_date = rs.getTimestamp("hire_date");
			emp.job_id = rs.getString("job_id");
			emp.salary = rs.getDouble("salary");
			emp.commission_pct = rs.getDouble("commission_pct");
			emp.manager_id = rs.getInt("manager_id");
			emp.department_id = rs.getInt("department_id");
			result.add(emp);
		}
		if (result.size() == 0) {
			System.out.println("\n입력하신 \"" + managerName + "\"이라는 부서장은 존재하지 않습니다.");
			System.out.println("처음으로 돌아갑니다.\n");
		} else {
			System.out.println("\n존재하는 직원은 총 " + result.size() + "명 입니다.");
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
			System.out.println("\n");
		}
		return result;
	}

	// 6번: 통계자료 출력 [부서의 빈도, 직무의 빈도, 부서별 평균근속 연수, 급여의 기술통계]
	
	static void getHRstatistics() throws Exception{
		Connection conn = DbUtil.getConnection();
		
		Scanner sc = new Scanner(System.in);
		String choose = "";
		
		while(choose != "out") {
			System.out.println("직무의 통계 정보 선택지<종료: out입력>\n"
					+ "1.부서의 빈도\n"
					+ "2.직무의 빈도\n"
					+ "3.부서별 평균근속 연수\n"
					+ "4.급여의 기술통계");
			System.out.print("입력: "); choose = sc.nextLine();
			switch(choose) {
			case "1":
				System.out.println("☆부서의 빈도 정보☆");
				String sql = "SELECT \r\n"
						+ "    e.department_id, d.department_name, COUNT(*) as department_mode\r\n"
						+ "FROM\r\n"
						+ "    employees e\r\n"
						+ "        LEFT OUTER JOIN\r\n"
						+ "    departments d USING (department_id)\r\n"
						+ "GROUP BY e.department_id";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery(); 
				while (rs.next()) {
					int department_id = rs.getInt("department_id");
					String department_name = rs.getString("department_name");
					int department_mode = rs.getInt("department_mode");
					
					
					
				}
				
				break;
			}
		}
	}
	
	
}
