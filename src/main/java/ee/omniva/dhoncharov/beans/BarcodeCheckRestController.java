package ee.omniva.dhoncharov.beans;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BarcodeCheckRestController {

    @GetMapping("/check")
    public boolean checkBarcode(@RequestParam("barcode") String barcode) {
        return false;
    }
}
