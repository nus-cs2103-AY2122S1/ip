import java.util.ArrayList;

public class Storage {
    private static ArrayList<String> storage = new ArrayList<>();
    public static void addReply(String userReply) {
        storage.add(userReply);
    }
    public static void listReply() {
        for (int i = 0; i < storage.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + storage.get(i));
        }
    }

}
