package me.sa1zer_.analyze;

import me.sa1zer_.analyze.exceptions.WrongAvailableException;
import me.sa1zer_.analyze.exceptions.WrongTimeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MainTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void main() throws WrongTimeException, WrongAvailableException {
        String[] argsV1 = {"-u", "99.9", "-t", "45", "-f", "access.log"};
        String[] argsV2 = {"-u", "99.9"};
        String[] argsV3 = {"-u", "99.9", "-t", "45", "-f", "access_big.log"};

        Main.main(argsV1);

        thrown.expect(WrongTimeException.class);
        Main.main(argsV2);

        Main.main(argsV3);
    }
}