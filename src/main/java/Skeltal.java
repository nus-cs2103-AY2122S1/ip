import java.util.Scanner;

public class Skeltal {

    private static void run() {
        TaskList.loadTaskList(Storage.loadFile());
        Ui.introduction();
        Scanner sc = new Scanner(System.in);
        boolean shutdown = false;
        while (!shutdown) {
            String userReply = sc.nextLine();
            if (userReply.equals("bye")) {
                shutdown = Ui.bye();
                sc.close();
                continue;
            } else {
                Parser.response(userReply);
            }
        }
    }
    public static void main(String[] args) {
        run();
    }
}
