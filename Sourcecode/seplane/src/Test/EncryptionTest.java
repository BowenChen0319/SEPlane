
import Toolbox.Encryption;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    @Test
    void check() throws Exception {

        for (int i = 0; i < 10; i++) {
            String str = RandomStringUtils.randomAlphanumeric(8);
            String stored = "";
            stored = Encryption.getSaltedHash(str);
            assertEquals(true, new Encryption().check(str, stored));
        }

    }
}
