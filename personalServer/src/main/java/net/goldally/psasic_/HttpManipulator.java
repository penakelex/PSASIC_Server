package net.goldally.psasic_;


import net.goldally.psasic_.requests.Request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpManipulator {
    private static HttpRequest.Builder builder;
    private static HttpClient client;

    public static final String globalServer = "http://127.0.0.1:8080/";

    public static void init() throws URISyntaxException { // TODO: Избежать HARDCODED ip сервера
        client = HttpClient.newHttpClient();
        builder = HttpRequest.newBuilder(new URI(globalServer));
    }

    public static HttpResponse<String> send(String command, Request data) throws IOException, InterruptedException, URISyntaxException {
        builder.uri(new URI(globalServer + command));
        HttpRequest request = builder.POST(HttpRequest.BodyPublishers.ofString(data.ToString())).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }
}
