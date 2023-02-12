import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class ConnectionManager {
    private String address;
    private final Map<String, String> parameters = new HashMap<>();

    public ConnectionManager(String address) {
        this.address = address;
    }

    public void addParameter(String parameter, String value) {
        parameters.put(parameter, value);
    }

    public String produceRequest() {
        String response = "";

        try {
            address +="?" + ParameterStringBuilder.getParamsString(parameters);
            HttpURLConnection connection = (HttpURLConnection) new URL(address).openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            response += content.toString();

            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
}
