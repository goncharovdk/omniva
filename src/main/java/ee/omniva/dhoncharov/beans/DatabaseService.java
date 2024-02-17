package ee.omniva.dhoncharov.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DatabaseService {

    @Value("${spring.datasource.url}")
    String connectionString;

    Connection conn = null;

    public void connect() {
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isConnected() {
        return conn != null;
    }

    public void disconnect() {
        try {
            if (isConnected()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getShipmentCount() {
        int result;
        String query = "SELECT COUNT(*) AS count FROM shipments";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getInt("count");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }

}
