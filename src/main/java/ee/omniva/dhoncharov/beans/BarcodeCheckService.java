package ee.omniva.dhoncharov.beans;

import org.springframework.stereotype.Service;

@Service
public class BarcodeCheckService {

    public boolean isValidBarcode(String barcode) {
        int len = barcode.length();
        return (len >= 13) && (len <= 25);
    }

    public boolean isInBloomFilter(String barcode) {
        return true;
    }

    public boolean isInDatabase(String barcode) {
        return false;
    }
}
