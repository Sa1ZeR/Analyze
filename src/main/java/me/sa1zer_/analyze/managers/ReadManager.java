package me.sa1zer_.analyze.managers;

import me.sa1zer_.analyze.exceptions.LogException;
import me.sa1zer_.analyze.objects.LogLine;
import me.sa1zer_.analyze.objects.OutputLogLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReadManager extends Thread {

    private File file;
    private double minAvailablePer;
    private double minTimeMc;

    private long failedCount;
    private long logCount;

    private Set<OutputLogLine> failedLogs;

    public ReadManager(File file, double minAvailablePer, double minTimeMc) {
        this.file = file;
        this.minAvailablePer = minAvailablePer;
        this.minTimeMc = minTimeMc;

        failedCount = 0;
        failedLogs = new HashSet<>();
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                LogLine logLine = new LogLine(line);
                logCount++;

                if(logLine.isFailed(minTimeMc)) {
                    failedCount++;
                    double per = calculatePercentage();
                    if(per < minAvailablePer) {
                        failedLogs.add(new OutputLogLine(per,
                                logLine.getStartTime(),
                                logLine.getEndTime()));
                    }
                }
            }

            br.close();

            failedLogs = failedLogs.stream().sorted(
                    Comparator.comparing(OutputLogLine::getStartTime)
            ).collect(Collectors.toCollection(LinkedHashSet::new));

            for(OutputLogLine l : failedLogs) {
                System.out.println(l.toString());
            }
        } catch (IOException | LogException e) {
            e.printStackTrace();
        }
    }

    private double calculatePercentage() {
        return 100.0 - ((double) failedCount * 100 / logCount);
    }

    public long getFailedCount() {
        return failedCount;
    }

    public Set<OutputLogLine> getFailedLogs() {
        return failedLogs;
    }
}
