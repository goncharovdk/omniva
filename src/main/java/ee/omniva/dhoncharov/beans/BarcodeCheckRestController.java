package ee.omniva.dhoncharov.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BarcodeCheckRestController {

    @GetMapping("/check")
    public boolean checkBarcode(@RequestParam("barcode") String barcode) {
        if (!isValidBarcode(barcode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid barcode");
        }
        return false;
    }

    private boolean isValidBarcode(String barcode) {
        int len = barcode.length();
        return (len >= 13) && (len <= 25);
    }
}
