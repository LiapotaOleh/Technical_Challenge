import com.google.gson.Gson;
public class User {
    private int user_id;
    private String display_name;
    private String location;
    private int answer_count;
    private int question_count;
    private String link;
    private String profile_image;
    private String[] tags;

    private int reputation;

    public int getReputation() {
        return reputation;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public String getLocation() {
        return location;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags() {
        ConnectionManager connectionManager =
                new ConnectionManager("https://api.stackexchange.com/2.3/users/" + user_id + "/tags");
        connectionManager.addParameter("site", "stackoverflow");
        String response = connectionManager.produceRequest();
        Tag[] tagsObjects = new Gson().fromJson(response, Tag[].class);
        tags = new String[tagsObjects.length];
        for (int i = 0; i < tags.length; i++) {
            tags[i] = tagsObjects[i].getName();
        }
    }

    public void setAnswer_count() {
        ConnectionManager connectionManager =
                new ConnectionManager("https://api.stackexchange.com/2.3/users/" + user_id + "/answers");
        connectionManager.addParameter("site", "stackoverflow");
        String response = connectionManager.produceRequest();
        answer_count = new Gson().fromJson(response, Object[].class).length;
    }

    public void setQuestion_count() {
        ConnectionManager connectionManager =
                new ConnectionManager("https://api.stackexchange.com/2.3/users/" + user_id + "/questions");
        connectionManager.addParameter("site", "stackoverflow");
        String response = connectionManager.produceRequest();
        question_count = new Gson().fromJson(response, Object[].class).length;
    }

    @Override
    public String toString() {
        StringBuilder tagsToString = new StringBuilder();
        for (String s : tags) {
            tagsToString.append(s).append(",");
        }
        tagsToString.replace(tagsToString.length() - 1, tagsToString.length(), "");
        return display_name + "|"
                + location + "|"
                + answer_count + "|"
                + question_count + "|"
                + tagsToString + "|"
                + link + "|"
                + profile_image;
    }

    private static class Tag{
        private String name;

        public String getName() {
            return name;
        }
    }
}
