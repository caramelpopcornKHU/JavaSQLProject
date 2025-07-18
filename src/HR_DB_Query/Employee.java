package HR_DB_Query;

import java.time.LocalDateTime;

public class Employee {
	int employee_id;
	String first_name;
	String last_name;
	String email;
	String phone_number;
	LocalDateTime hire_date;
	String job_id;
	double salary;
	double commission_pct;
	int manager_id;
	int department_id;
	
	
	
	/* hire_date는 resultSet에서 저렇게 받아와야해.
	 * Timestamp ts = resultSet.getTimestamp("hire_date");
	 * LocalDateTime hireDate = ts.toLocalDateTime();
	 * 
	 * 
	 * */
	
}
