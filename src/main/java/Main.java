public class Main {
    public static void main(String[] args) {
        ConnectionManager manager = new ConnectionManager("https://api.stackexchange.com/2.3/users?");
        manager.addParameter("fromdate", "1298764800");
        manager.addParameter("todate", "1298851200");
        manager.addParameter("order", "desc");
        manager.addParameter("sort", "reputation");
        manager.addParameter("site", "stackoverflow");
        String test = manager.produceRequest();
        System.out.println(test);
    }
}
