package HR_DB_Query;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Employee {
	int employee_id;
	String first_name;
	String last_name;
	String email;
	String phone_number;
	Timestamp hire_date;
	String job_id;
	double salary;
	double commission_pct;
	int manager_id;
	int department_id;
	String city;
	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", phone_number=" + phone_number + ", hire_date=" + hire_date + ", job_id="
				+ job_id + ", salary=" + salary + ", commission_pct=" + commission_pct + ", manager_id=" + manager_id
				+ ", department_id=" + department_id + ", city= " + city + "]" ;
	}
	
	
	
	/* hire_date는 resultSet에서 저렇게 받아와야해.
	 * Timestamp ts = resultSet.getTimestamp("hire_date");
	 * LocalDateTime hireDate = ts.toLocalDateTime();
	 * 
	 * 
	 * */
	
}
