package net.goldally.psasic_;

import net.goldally.psasic_.responces.IsSession;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class httpManipulator {
    public static final String globalServer = "http://127.0.0.1:8080/";

    public static String send(String command, Map<String, String> params) throws IOException {
        URL url = new URL("http://127.0.0.1:8080/" + command);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        StringBuilder requestData = new StringBuilder();

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (requestData.length() != 0) {
                requestData.append('&');
            }
            requestData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            requestData.append('=');
            requestData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] requestDataBytes = requestData.toString().getBytes("UTF-8");

        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length", String.valueOf(requestDataBytes.length));

        connection.setDoOutput(true);

        try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
            writer.write(requestDataBytes);
        }

        StringBuilder content;

        try (BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            content = new StringBuilder();
            while ((line = input.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } finally {
            connection.disconnect();
        }
        return content.toString();
    }
}
