package net.goldally.psasic_;

import net.goldally.psasic_.requests.Request;
import net.goldally.psasic_.responces.IsSession;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Sessions extends Request {
    public static boolean isSession(String authKey) throws IOException, URISyntaxException, InterruptedException {
        URL url = new URL("http://127.0.0.1:8080/isSession");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        Map<String, String> params = new HashMap<>();
        params.put("authKey", "5e6f8c18bd4b992c7328bf34cb68c9170f9c44084d8f9c4bb388c24cf8db9646");

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

        System.out.println(content.toString());
        IsSession result = PsasicMain.gson.fromJson(content.toString(), IsSession.class);
        return result.state;
    }
}
