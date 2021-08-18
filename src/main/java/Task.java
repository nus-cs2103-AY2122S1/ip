import java.util.ArrayList;

public class Task {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static ArrayList<Boolean> doneList = new ArrayList<>();

    public static void addReply(String userReply) {
        tasks.add(userReply);
        doneList.add(false);
    }
    public static void listReply() {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            String tick = doneList.get(i) ? "[X] " : "[ ] ";
            System.out.println(tick + num + ". " + tasks.get(i));
        }
    }

    public static void done(String index_1) {
        int i = Integer.parseInt(index_1) - 1;
        int num = i + 1;
        doneList.set(i, true);
        String tick = doneList.get(i) ? "[X] " : "[ ] ";
        System.out.println("Done! I've marked this task as done! \n" +
               tick + num + ". " + tasks.get(i));
    }

}
