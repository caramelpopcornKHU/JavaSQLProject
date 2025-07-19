package HR_DB_Query2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // claude revised: SQLException import 추가
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import HR_DB_Query.DbUtil;
import HR_DB_Query.Employee;

public class employee_dao_refactored {

    // claude revised: Employee 객체 생성을 공통 메소드로 분리
    private static Employee createEmployeeFromResultSet(ResultSet rs) throws SQLException {
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
        return emp;
    }

    // claude revised: 결과 출력을 공통 메소드로 분리
    private static void printEmployeeList(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.println("\n총 " + employees.size() + "명의 직원이 검색되었습니다.");
            System.out.println("=".repeat(80)); // claude revised: 구분선 추가
            for (Employee emp : employees) { // claude revised: enhanced for loop 사용
                System.out.println(emp);
            }
            System.out.println("=".repeat(80));
        }
    }

    // 1: 직원 이름으로 직원 정보 검색
    public static List<Employee> findByName(String name) {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";
        
        // claude revised: try-with-resources를 더 명확하게 사용
        try (Connection conn = DbUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String keyword = "%" + name + "%";
            ps.setString(1, keyword);
            ps.setString(2, keyword);
            
            try (ResultSet rs = ps.executeQuery()) { // claude revised: ResultSet도 try-with-resources로 관리
                while (rs.next()) {
                    result.add(createEmployeeFromResultSet(rs)); // claude revised: 공통 메소드 사용
                }
            }
            
            printEmployeeList(result); // claude revised: 공통 출력 메소드 사용
            
        } catch (Exception e) {
            System.err.println("이름 검색 중 오류 발생: " + e.getMessage()); // claude revised: 더 구체적인 에러 메시지
        }
        return result;
    }

    // 2: 입사년도별로 직원 검색
    public static List<Employee> findByHireYear(int year) {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE YEAR(hire_date) = ?";
        
        try (Connection conn = DbUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, year);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(createEmployeeFromResultSet(rs));
                }
            }
            
            printEmployeeList(result);
            
        } catch (Exception e) {
            System.err.println("입사년도 검색 중 오류 발생: " + e.getMessage());
        }
        return result;
    }

    // 3: 부서번호별로 직원 검색
    public static List<Employee> findByDeptId(int deptId) {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ?";
        
        try (Connection conn = DbUtil.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, deptId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(createEmployeeFromResultSet(rs));
                }
            }
            
            printEmployeeList(result);
            
        } catch (Exception e) {
            System.err.println("부서번호 검색 중 오류 발생: " + e.getMessage());
        }
        return result;
    }

    // 4: 직무로 검색 메서드
    public static List<Employee> getEmpListByJobId(String jobId) throws Exception {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE job_id = ?";
        
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, jobId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(createEmployeeFromResultSet(rs));
                }
            }
            
            // claude revised: 출력 메시지 개선
            if (result.isEmpty()) {
                System.out.println("\n입력하신 \"" + jobId + "\"라는 직업명은 존재하지 않습니다.");
            } else {
                System.out.println("\n존재하는 직원은 총 " + result.size() + "명입니다.");
                System.out.println("=".repeat(80));
                for (Employee emp : result) {
                    System.out.println(emp);
                }
                System.out.println("=".repeat(80));
            }
        }
        return result;
    }

    // 5: 도시로 검색
    public static List<Employee> getEmpListByCityName(String cityName) throws Exception {
        List<Employee> result = new ArrayList<>();
        // claude revised: SQL 쿼리 가독성 개선
        String sql = "SELECT e.*, l.city " +
                    "FROM employees e " +
                    "LEFT JOIN departments d USING(department_id) " +
                    "LEFT JOIN locations l USING(location_id) " +
                    "WHERE l.city = ?";
        
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cityName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = createEmployeeFromResultSet(rs);
                    emp.city = rs.getString("city"); // claude revised: 도시 정보 추가
                    result.add(emp);
                }
            }
            
            if (result.isEmpty()) {
                System.out.println("\n입력하신 \"" + cityName + "\"에는 직원이 존재하지 않습니다.");
            } else {
                System.out.println("\n존재하는 직원은 총 " + result.size() + "명입니다.");
                System.out.println("=".repeat(80));
                for (Employee emp : result) {
                    System.out.println(emp);
                }
                System.out.println("=".repeat(80));
            }
        }
        return result;
    }

    // 6: 통계자료 출력
    static void getHRstatistics() throws Exception {
        try (Connection conn = DbUtil.getConnection();
             Scanner sc = new Scanner(System.in)) { // claude revised: Scanner도 try-with-resources로 관리
            
            String choose = "";
            
            // claude revised: String 비교를 equals()로 변경
            while (!choose.equals("out")) {
                displayStatisticsMenu(); // claude revised: 메뉴를 별도 메소드로 분리
                System.out.print("입력: ");
                choose = sc.nextLine().trim();
                
                switch (choose) {
                    case "1":
                        showDepartmentFrequency(conn);
                        break;
                    case "2":
                        showJobFrequency(conn);
                        break;
                    case "3":
                        showDepartmentSeniority(conn);
                        break;
                    case "4":
                        showSalaryStatistics(conn);
                        break;
                    case "out":
                        System.out.println("통계 메뉴를 종료합니다.");
                        break;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                        break;
                }
            }
        }
    }

    // claude revised: 통계 메뉴 출력을 별도 메소드로 분리
    private static void displayStatisticsMenu() {
        System.out.println("\n=== 통계 정보 선택 (종료: out 입력) ===");
        System.out.println("1. 부서의 빈도");
        System.out.println("2. 직무의 빈도");
        System.out.println("3. 부서별 평균근속 연수");
        System.out.println("4. 급여의 기술통계");
    }

    // claude revised: 부서 빈도 통계를 별도 메소드로 분리
    private static void showDepartmentFrequency(Connection conn) throws SQLException {
        System.out.println("\n☆부서의 빈도 정보☆");
        String sql = "SELECT e.department_id, d.department_name, COUNT(*) as department_count " +
                    "FROM employees e " +
                    "LEFT OUTER JOIN departments d USING (department_id) " +
                    "GROUP BY e.department_id " +
                    "ORDER BY department_count DESC"; // claude revised: 정렬 추가
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.printf("%-10s %-20s %-10s%n", "부서번호", "부서이름", "인원수");
            System.out.println("-".repeat(45));
            
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-10d%n",
                    rs.getInt("department_id"),
                    rs.getString("department_name"),
                    rs.getInt("department_count"));
            }
        }
    }

    // claude revised: 직무 빈도 통계를 별도 메소드로 분리
    private static void showJobFrequency(Connection conn) throws SQLException {
        System.out.println("\n☆직무의 빈도 정보☆");
        String sql = "SELECT job_id, COUNT(*) AS job_count " +
                    "FROM employees " +
                    "GROUP BY job_id " +
                    "ORDER BY job_count DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.printf("%-15s %-10s%n", "직무이름", "인원수");
            System.out.println("-".repeat(30));
            
            while (rs.next()) {
                System.out.printf("%-15s %-10d%n",
                    rs.getString("job_id"),
                    rs.getInt("job_count"));
            }
        }
    }

    // claude revised: 부서별 근속연수 통계를 별도 메소드로 분리
    private static void showDepartmentSeniority(Connection conn) throws SQLException {
        System.out.println("\n☆부서별 평균근속 연수☆");
        String sql = "SELECT d.department_name, " +
                    "AVG(TIMESTAMPDIFF(YEAR, e.hire_date, CURDATE())) AS seniority " +
                    "FROM departments d " +
                    "RIGHT OUTER JOIN employees e USING (department_id) " +
                    "GROUP BY d.department_id " +
                    "ORDER BY seniority DESC";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.printf("%-20s %-15s%n", "부서이름", "평균근속연수");
            System.out.println("-".repeat(40));
            
            while (rs.next()) {
                System.out.printf("%-20s %-15.2f%n",
                    rs.getString("department_name"),
                    rs.getDouble("seniority"));
            }
        }
    }

    // claude revised: 급여 통계를 별도 메소드로 분리
    private static void showSalaryStatistics(Connection conn) throws SQLException {
        System.out.println("\n☆급여의 기술통계☆");
        String sql = "SELECT COUNT(*) as total_employees, " +
                    "TRUNCATE(MIN(salary),0) as min_salary, " +
                    "TRUNCATE(MAX(salary),0) as max_salary, " +
                    "ROUND(AVG(salary)) as avg_salary, " +
                    "ROUND(STDDEV(salary),2) as stddev_salary " +
                    "FROM employees";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                System.out.printf("전사 직원 수: %d명%n", rs.getInt("total_employees"));
                System.out.printf("최소 급여: $%,d%n", rs.getInt("min_salary"));
                System.out.printf("최대 급여: $%,d%n", rs.getInt("max_salary"));
                System.out.printf("평균 급여: $%,d%n", rs.getInt("avg_salary"));
                System.out.printf("급여 표준편차: $%,.2f%n", rs.getDouble("stddev_salary"));
            }
        }
    }

    // 7: 부서장 이름으로 검색
    public static List<Employee> getEmpListByManagerName(String managerName) throws Exception {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT * FROM employees " +
                    "WHERE department_id IN (" +
                    "    SELECT department_id FROM departments " +
                    "    WHERE manager_id IN (" +
                    "        SELECT employee_id FROM employees " +
                    "        WHERE last_name = ?" +
                    "    )" +
                    ")";
        
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, managerName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(createEmployeeFromResultSet(rs));
                }
            }
            
            if (result.isEmpty()) {
                System.out.println("\n입력하신 \"" + managerName + "\"이라는 부서장은 존재하지 않습니다.");
            } else {
                System.out.println("\n존재하는 직원은 총 " + result.size() + "명입니다.");
                System.out.println("=".repeat(80));
                for (Employee emp : result) {
                    System.out.println(emp);
                }
                System.out.println("=".repeat(80));
            }
        }
        return result;
    }

    // 8: 나라 이름으로 그 나라에 근무하는 직원정보를 검색
    public static List<Employee> getEmpListByCountryName(String countryName) throws Exception {
        List<Employee> result = new ArrayList<>();
        String sql = "SELECT e.*, l.city " +
                    "FROM countries c " +
                    "JOIN locations l USING (country_id) " +
                    "JOIN departments d USING (location_id) " +
                    "RIGHT OUTER JOIN employees e USING(department_id) " +
                    "WHERE c.country_name = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, countryName);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Employee emp = createEmployeeFromResultSet(rs);
                    emp.city = rs.getString("city");
                    result.add(emp);
                }
            }
            
            if (result.isEmpty()) {
                System.out.println("\n입력하신 \"" + countryName + "\"에는 직원이 존재하지 않습니다.");
            } else {
                System.out.println("\n존재하는 직원은 총 " + result.size() + "명입니다.");
                System.out.println("=".repeat(80));
                for (Employee emp : result) {
                    System.out.println(emp);
                }
                System.out.println("=".repeat(80));
            }
        }
        return result;
    }
}