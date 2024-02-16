package ee.omniva.dhoncharov;

import ee.omniva.dhoncharov.beans.BarcodeCheckRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OmnivaApplicationTests {

    @Autowired
    BarcodeCheckRestController controller;

    @Test
    void contextLoads() {
        assertNotNull(controller);
    }

    @Test
    void checkEmptyBarcode() {
        assertFalse(controller.checkBarcode(""));
    }

}
