package ee.omniva.dhoncharov.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BarcodeCheckRestController {

    @Autowired
    BarcodeCheckService service;

    @GetMapping("/isused")
    public boolean isUsed(@RequestParam("barcode") String barcode) {
        if (!service.isValidBarcode(barcode)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid barcode");
        }

        if (!service.isInBloomFilter(barcode)) {
            // No false negatives from Bloom filter
            return false;
        } else {
            // Additional check needed for possible false positive
            return service.isInDatabase(barcode);
        }
    }
}
