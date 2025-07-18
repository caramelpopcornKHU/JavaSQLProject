package HR_DB_Query;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {
	private static String url;
	private static String user;
	private static String password;
	static {
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("src/main/resources/db.properties"));
			url = props.getProperty("db.url");
			user = props.getProperty("db.user");
			password = props.getProperty("db.password");
		} catch (Exception e) {
			throw new RuntimeException("DB 설정 파일 로드 실패", e);
		}
	}

	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, password);
	}
}

/*	파일 이름: db.properties
 * 
 * db.url=jdbc:mysql://localhost:3306/newhr?serverTimezone=UTC 
 * db.user=root
 * db.password=
 * 
 * 
 */
