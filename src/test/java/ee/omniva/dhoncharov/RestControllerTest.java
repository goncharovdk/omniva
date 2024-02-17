package ee.omniva.dhoncharov;

import ee.omniva.dhoncharov.beans.BarcodeCheckRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestControllerTest {

    @Autowired
    BarcodeCheckRestController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    void checkEmptyBarcode() {
        assertThrows(ResponseStatusException.class, () -> controller.isUsed(""));
    }

    @Test
    void checkUsedBarcode() {
        assertTrue(controller.isUsed("THISISAUSEDBARCODE"));
    }

    @Test
    void checkNotUsedBarcode() {
        assertFalse(controller.isUsed("THISISANOTUSEDBARCODE"));
    }
}
