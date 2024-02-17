package ee.omniva.dhoncharov.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class DatabaseService {

    @Value("${spring.datasource.url}")
    String connectionString;

    Connection conn = null;

    @PostConstruct
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

    @PreDestroy
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

    public boolean isBarcodeInDb(String barcode) {
        int result;
        String query = "SELECT COUNT(*) AS count FROM shipments where barcode = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, barcode);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            result = rs.getInt("count");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return (result != 0);
    }

    public void provideBarcodes(BarcodeCheckService service) {
        String query = "SELECT barcode FROM shipments";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            // TODO: Supposedly, fetch size to be set here, as 500 mln records implied.
            // rs.setFetchSize(10000);

            while (rs.next()) {
                service.loadBarcode(rs.getString("barcode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
