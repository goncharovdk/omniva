package ee.omniva.dhoncharov.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
