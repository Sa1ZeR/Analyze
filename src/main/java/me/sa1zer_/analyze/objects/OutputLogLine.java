package me.sa1zer_.analyze.objects;

public class OutputLogLine {

    private double percentage;
    private String startTime;
    private String endTime;

    public OutputLogLine(double percentage, String startTime, String endTime) {
        this.percentage = percentage;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public double getPercentage() {
        return percentage;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return startTime + "\t" + endTime + "\t" + percentage;
    }
}
