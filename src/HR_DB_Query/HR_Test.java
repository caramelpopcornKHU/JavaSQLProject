package HR_DB_Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HR_Test {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 
				DriverManager.getConnection("jdbc:mysql://localhost:3306/newhr", "root", "rootroot");
		String sql = "select * from employees where first_name = ? and last_name = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "first_name");
		stmt.setString(2, "last_name");
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			String lastName=rs.getString("last_name");
			System.out.println(lastName);
		}
	}

}
