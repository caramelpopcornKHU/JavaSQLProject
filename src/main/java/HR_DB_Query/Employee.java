package HR_DB_Query;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Employee {
	public int employee_id;
	public String first_name;
	public String last_name;
	public String email;
	public String phone_number;
	public Timestamp hire_date;
	public String job_id;
	public double salary;
	public double commission_pct;
	public int manager_id;
	public int department_id;
	public String city;

	public Employee() {

	}

	public Employee(int employee_id, String first_name, String last_name, String email, String job_id, double salary,
			java.sql.Date hire_date) {
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.job_id = job_id;
		this.salary = salary;
// java.sql.Date -> java.sql.Timestamp 변환
		if (hire_date != null) {
			this.hire_date = new java.sql.Timestamp(hire_date.getTime());
		} else {
			this.hire_date = null;
		}
	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", job_id="
				+ job_id + ", salary=" + salary + ", commission_pct=" + commission_pct + ", manager_id=" + manager_id
				+ ", department_id=" + department_id + ", city= " + city + "]";
	}
	
	public String toPrettyString() {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    DecimalFormat moneyFormat = new DecimalFormat("#,###.00");

	    return String.format(
	        "[직원ID] %d | 이름: %s %s | 이메일: %s | 전화: %s\n" +
	        "입사일: %s | 직무: %s | 급여: $%s | 커미션: %s\n" +
	        "매니저 ID: %s | 부서번호: %s | 도시: %s",
	        employee_id,
	        first_name,
	        last_name,
	        email,
	        phone_number != null ? phone_number : "정보 없음",
	        hire_date != null ? dateFormat.format(hire_date) : "정보 없음",
	        job_id != null ? job_id : "정보 없음",
	        moneyFormat.format(salary),
	        commission_pct > 0 ? commission_pct : "없음",
	        manager_id > 0 ? manager_id : "정보 없음",
	        department_id > 0 ? department_id : "정보 없음",
	        city != null ? city : "정보 없음"
	    );
	}
	
	
	
}
