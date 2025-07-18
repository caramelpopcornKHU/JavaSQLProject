package HR_DB_Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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

}
