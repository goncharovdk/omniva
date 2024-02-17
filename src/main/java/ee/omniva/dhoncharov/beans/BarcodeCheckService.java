package ee.omniva.dhoncharov.beans;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BarcodeCheckService {

    @Autowired
    DatabaseService database;

    private BloomFilter<String> filter;

    public boolean isValidBarcode(String barcode) {
        int len = barcode.length();
        return (len >= 13) && (len <= 25);
    }

    public boolean isInBloomFilter(String barcode) {
        return filter.mightContain(barcode);
    }

    public boolean isInDatabase(String barcode) {
        return database.isBarcodeInDb(barcode);
    }

    @PostConstruct
    private void initBloomFilter() {

        final double falsePositiveProbability = 0.01;
        int expectedInsertions = database.getShipmentCount();

        filter = BloomFilter.create(
                Funnels.stringFunnel(StandardCharsets.UTF_8),
                expectedInsertions,
                falsePositiveProbability);

        loadAllBarcodes();
    }

    public void loadBarcode(String barcode) {
       // NB: This method is public, as the filter is to be updated
       // from outside when new shipments are added to the database.
        filter.put(barcode);
    }

    private void loadAllBarcodes() {
        database.provideBarcodes(this);
    }
}
