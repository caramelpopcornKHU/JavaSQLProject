package HR_DB_Query;

import java.sql.Timestamp;

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
}
