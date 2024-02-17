package ee.omniva.dhoncharov.beans;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BarcodeCheckService {

    @Autowired
    DatabaseService database;

    private BloomFilter<String> filter;

    public BarcodeCheckService() {
        // TODO: calculate expected insertions
        initBloomFilter(5, 0.01);
    }

    public boolean isValidBarcode(String barcode) {
        int len = barcode.length();
        return (len >= 13) && (len <= 25);
    }

    public boolean isInBloomFilter(String barcode) {
        return filter.mightContain(barcode);
    }

    public boolean isInDatabase(String barcode) {
        return true;
    }

    private void initBloomFilter(int expectedInsertions, double fpp) {
        filter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedInsertions,
                fpp);

        // TODO: Load all barcodes
        filter.put("THISISAUSEDBARCODE");
    }
}
