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
    void testCount() {
        // TODO: This test is for development only. To be remowed afterwards.
        assertEquals(100, database.getShipmentCount());
    }

    @Test
    void testIsBarcodeInDb() {
        assertTrue(database.isBarcodeInDb("THISISAUSEDBARCODE"));
    }
}