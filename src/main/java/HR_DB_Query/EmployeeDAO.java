package HR_DB_Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDAO {


	// 1: 직원 이름으로 직원 정보 검색
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

	// 2: 입사년도별로 직원 검색
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

	// 3: 부서번호별로 직원 검색
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

	// 4: 직무로 검색 메서드
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

	
	// 5: 도시로 검색
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


	// 6: 통계자료 출력 [부서의 빈도, 직무의 빈도, 부서별 평균근속 연수, 급여의 기술통계]
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
					System.out.printf("부서 번호: %d\t부서 이름: %s\t부서 인원: %d\n",department_id,department_name,department_mode);
				}
				
				break;
			case "2":
				System.out.println("☆직무의 빈도 정보☆");
				String sql2 = "SELECT \r\n"
						+ "    job_id, COUNT(*) AS job_count\r\n"
						+ "FROM\r\n"
						+ "    employees\r\n"
						+ "GROUP BY job_id";
				PreparedStatement stmt2 = conn.prepareStatement(sql2);
				ResultSet rs2 = stmt2.executeQuery(); 
				while (rs2.next()) {
					String job_id = rs2.getString("job_id");
					int job_count = rs2.getInt("job_count");
					System.out.printf("직무 이름: %s\t직무 인원: %d\n",job_id,job_count);
				}
				
				break;
				
				
			case "3":
				System.out.println("☆부서별 평균근속 연수☆");
				String sql3 = "SELECT \r\n"
						+ "    d.department_name,\r\n"
						+ "    AVG(TIMESTAMPDIFF(YEAR,\r\n"
						+ "        e.hire_date,\r\n"
						+ "        CURDATE())) AS seniority\r\n"
						+ "FROM\r\n"
						+ "    departments d\r\n"
						+ "        RIGHT OUTER JOIN\r\n"
						+ "    employees e USING (department_id)\r\n"
						+ "GROUP BY d.department_id\r\n"
						+ "ORDER BY AVG(TIMESTAMPDIFF(YEAR,\r\n"
						+ "    e.hire_date,\r\n"
						+ "    CURDATE()))";
				PreparedStatement stmt3 = conn.prepareStatement(sql3);
				ResultSet rs3 = stmt3.executeQuery(); 
				while (rs3.next()) {
					String department_name2 = rs3.getString("department_name");
					double seniority = rs3.getDouble("seniority");
					System.out.printf("부서 이름: %s\t평균 근속 연수: %.2f\n",department_name2,seniority);
				}
				
				break;
			case "4":
				System.out.println("☆급여의 기술통계☆");
				String sql4 = "select \r\n"
						+ "	 count(*) as total_employees\r\n"
						+ "    ,truncate(min(salary),0)  as min_salary\r\n"
						+ "    ,truncate(max(salary),0) as max_salary\r\n"
						+ "    ,round(avg(salary)) as avg_salary\r\n"
						+ "    ,round(stddev(salary),2) as stddev_salary\r\n"
						+ "from employees";
				PreparedStatement stmt4 = conn.prepareStatement(sql4);
				ResultSet rs4 = stmt4.executeQuery(); 
				while (rs4.next()) {
					int total_employees = rs4.getInt("total_employees");
					int min_salary = rs4.getInt("min_salary");
					int max_salary = rs4.getInt("max_salary");
					int avg_salary = rs4.getInt("avg_salary");
					double stddev_salary = rs4.getDouble("stddev_salary");
					System.out.printf("전사 직원의 수:%d\t최소 급여:%d\t최대 급여:%d\t평균 급여:%d\t급여 표준편차:%.2f\n", total_employees,
							min_salary, max_salary, avg_salary, stddev_salary);
					
				}
				
				break;
			case "out":
				choose = "out";
				break;
			}
		}
	}
	
	// 7: 부서장 이름으로 검색
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
	
	// 8: 나라 이름으로 그 나라에 근무하는 직원정보를 검색
	public static List<Employee> getEmpListByCountryName(String countryName) throws Exception{
		List<Employee> result = new ArrayList<Employee>();
		Connection conn = DbUtil.getConnection();
		
		String sql = "SELECT * FROM countries c " +
	             "JOIN locations l USING (country_id) " +
	             "JOIN departments d USING (location_id) " +
	             "RIGHT OUTER JOIN employees e USING(department_id) " +
	             "WHERE c.country_name = ?";

		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, countryName);
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
			System.out.println("\n입력하신 \"" + countryName + "\"에는 직원이 존재하지 않습니다.");
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

	
	
}
