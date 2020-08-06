

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class HTTPworker implements Runnable {

    private final LinkedBlockingQueue queue;

    public HTTPworker(LinkedBlockingQueue queue) {        
        this.queue = queue;
    }

    private String getResponse(String url) throws IOException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        StringBuilder response;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String data = (String) queue.take();
                String response = getResponse(data);
                //Do something with response
                System.out.println(response);
            } catch (InterruptedException | IOException ex) {
                //Handle exception
            }
        }
    }
}
