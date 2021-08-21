package me.sa1zer_.analyze.objects;

import me.sa1zer_.analyze.exceptions.LogException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogLineTest {

    @Test
    public void isFailed() {
        LogLine logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertTrue(logLine.isFailed(45D));

        logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 501 2 15.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertTrue(logLine.isFailed(45D));

        logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 13.415114 \"-\" \"@list-item-updater\" prio:0");
        assertFalse(logLine.isFailed(45D));
    }

    @Test
    public void getStartTime() throws LogException {
        LogLine logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertEquals(logLine.getStartTime(), "16:47:03");
    }

    @Test
    public void getEndTime() throws LogException {
        LogLine logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertEquals(logLine.getEndTime(), "16:47:03");

        logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 1135.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertEquals(logLine.getEndTime(), "16:47:04");

        logLine = new LogLine("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 4135.415114 \"-\" \"@list-item-updater\" prio:0");
        Assert.assertEquals(logLine.getEndTime(), "16:47:07");
    }
}