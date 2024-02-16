package ee.omniva.dhoncharov.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*
* NB: ORM is not required as we are working only with barcode field.
* */

@Service
public class DatabaseService {

    @Value("${spring.datasource.url}")
    String connectionString;

}
