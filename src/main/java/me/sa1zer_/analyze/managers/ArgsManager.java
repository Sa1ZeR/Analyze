package me.sa1zer_.analyze.managers;

import me.sa1zer_.analyze.exceptions.WrongAvailableException;
import me.sa1zer_.analyze.exceptions.WrongTimeException;

import java.util.Arrays;
import java.util.List;

public class ArgsManager {

    private List<String> args;;

    private String fileName;
    private double minAvailablePercentage;
    private double minTimeInMc;

    public ArgsManager(String[] args) {
        this.args = Arrays.asList(args);

        fileName = "access.log"; //set default filename
    }

    public void verifyStart() throws WrongTimeException, WrongAvailableException {
        if(argsIsEmpty()) throw new IllegalArgumentException("Arguments count must be greater than 0");
        readArgs();
    }

    private boolean argsIsEmpty() {
        return args.size() == 0;
    }

    private void readArgs() throws WrongAvailableException, WrongTimeException {
        if(!args.contains("-u"))
            throw new WrongAvailableException("Parameter -u is not specified, use: -u <double value>" +
                    " (minimum acceptable level (percentage))");
        minAvailablePercentage = Double.parseDouble(args.get(args.indexOf("-u") + 1));

        if(!args.contains("-t"))
            throw new WrongTimeException("Parameter -t is not specified, use: -t <double value>" +
                    " (acceptable response time in milliseconds)");
        minTimeInMc = Double.parseDouble(args.get(args.indexOf("-t") + 1));

        if(args.contains("-f"))
            fileName = args.get(args.indexOf("-f") + 1);
    }

    public double getMinAvailablePercentage() {
        return minAvailablePercentage;
    }

    public double getMinTimeInMc() {
        return minTimeInMc;
    }

    public String getFileName() {
        return fileName;
    }
}
