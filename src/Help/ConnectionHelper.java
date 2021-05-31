package Help;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class ConnectionHelper {
	Connection connection;
	public Connection connectionHelper() {
		try {
			return this.connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root","454078");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
