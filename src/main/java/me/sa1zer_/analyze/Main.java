package me.sa1zer_.analyze;

import me.sa1zer_.analyze.exceptions.WrongAvailableException;
import me.sa1zer_.analyze.exceptions.WrongTimeException;
import me.sa1zer_.analyze.managers.ArgsManager;
import me.sa1zer_.analyze.managers.ReadManager;

import java.io.File;

public class Main {

    public static void main(String[] args) throws WrongTimeException, WrongAvailableException {
        ArgsManager argsManager = new ArgsManager(args);
        argsManager.verifyStart();

        ReadManager readManager = new ReadManager(new File(argsManager.getFileName()),
                argsManager.getMinAvailablePercentage(), argsManager.getMinTimeInMc());
        readManager.start();
    }
}
