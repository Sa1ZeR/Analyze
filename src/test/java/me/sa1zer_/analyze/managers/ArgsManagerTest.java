package me.sa1zer_.analyze.managers;

import me.sa1zer_.analyze.exceptions.WrongAvailableException;
import me.sa1zer_.analyze.exceptions.WrongTimeException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ArgsManagerTest {

    ArgsManager argsManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test()
    public void verifyStart() throws WrongTimeException, WrongAvailableException {
        String[] argsV1 = {"-u", "99.9", "-t", "45"};
        String[] argsV2 = {"-t", "45", "-u", "99.7"};
        String[] argsV3 = {"-u", "99.9", "-t", "45", "-f", "access.log"};
        String[] argsV4 = {"-t", "45", "-f", "access.log"};
        String[] argsV5 = {"-u", "99.9"};

        argsManager = new ArgsManager(argsV1);
        argsManager.verifyStart();
        argsManager = new ArgsManager(argsV2);
        argsManager.verifyStart();
        argsManager = new ArgsManager(argsV3);
        argsManager.verifyStart();

        Assert.assertEquals("access.log", argsManager.getFileName());
        Assert.assertEquals(99.9, argsManager.getMinAvailablePercentage(), 0.0);
        Assert.assertEquals(45, argsManager.getMinTimeInMc(), 0.0);

        argsManager = new ArgsManager(argsV4);
        thrown.expect(WrongAvailableException.class);
        argsManager.verifyStart();

        argsManager = new ArgsManager(argsV5);
        thrown.expect(WrongTimeException.class);
        argsManager.verifyStart();

        argsManager = new ArgsManager(new String[]{});
        thrown.expect(IllegalArgumentException.class);
        argsManager.verifyStart();
    }
}