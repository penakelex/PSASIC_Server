package net.goldally.psasic_.misc;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.*;

public class Reader {
    public static String readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String text = null;
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        text = sb.toString();
        br.close();

        return text;
    }
}
