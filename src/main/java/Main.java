public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager("https://api.stackexchange.com/2.3/users");
        connectionManager.addParameter("site", "stackoverflow");
        String response = connectionManager.produceRequest();
    }
}
