package me.sa1zer_.analyze.managers;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ReadManagerTest {

    ReadManager readManager;

    @Test
    public void run() throws InterruptedException {
        File file = new File("access_test.log");

        List<String> lines = new ArrayList<>();
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=7ae28555 HTTP/1.1\" 503 2 23.251219 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e356713 HTTP/1.1\" 200 2 30.164372 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6c21c8f6 HTTP/1.1\" 200 2 33.02583 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=cceed874 HTTP/1.1\" 503 2 35.249855 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=4b84a53c HTTP/1.1\" 200 2 56.783072 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=39b3d39 HTTP/1.1\" 501 2 97.679409 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=bd456187 HTTP/1.1\" 200 2 43.328125 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=ea57e6e1 HTTP/1.1\" 200 2 39.73486 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=15b50208 HTTP/1.1\" 200 2 1595.573017 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=407a047a HTTP/1.1\" 200 2 108.334488 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e156f7e HTTP/1.1\" 200 2 69.669118 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=b0f9ca08 HTTP/1.1\" 200 2 58.291168 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=75ce777 HTTP/1.1\" 200 2 64.756698 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:03 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=e3fcf288 HTTP/1.1\" 501 2 85.198348 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:04 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=13e403e1 HTTP/1.1\" 501 2 3531.88532 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:04 +1000] \"PUT /rest/v1.4/documents?zone=archive&_rid=c072bfbc HTTP/1.1\" 200 2 43.282385 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:04 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=d6a53eb8 HTTP/1.1\" 200 2 72.353028 \"-\" \"@list-item-updater\" prio:0");
        lines.add("192.168.32.181 - - [14/06/2017:16:47:04 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6db8fda9 HTTP/1.1\" 200 2 64.415114 \"-\" \"@list-item-updater\" prio:0");

        writeToFile(file, lines);

        readManager = new ReadManager(file,99.9, 45);
        readManager.start();

        readManager.join();
        Assert.assertEquals(readManager.getFailedCount(), readManager.getFailedLogs().size());
        Assert.assertEquals(readManager.getFailedCount(), 13);
    }

    @Test
    public void getFailedCount() {
    }

    @Test
    public void getFailedLogs() {
    }

    private void writeToFile(File file, List<String> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for(String s : list) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}