import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
        StringBuilder response = new StringBuilder();

        try {
            address += ParameterStringBuilder.getParamsString(parameters);
            HttpURLConnection connection = (HttpURLConnection) new URL(address).openConnection();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.toString();
    }
}
