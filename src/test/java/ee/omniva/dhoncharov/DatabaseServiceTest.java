package ee.omniva.dhoncharov;

import ee.omniva.dhoncharov.beans.DatabaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseServiceTest {

    @Autowired
    DatabaseService database;

    @Test
    void testConnect() {
        database.connect();
        database.disconnect();
    }

    @Test
    void testCount() {
        int shipmentCount;

        database.connect();
        shipmentCount = database.getShipmentCount();
        database.disconnect();

        assertEquals(1, shipmentCount);
    }
}