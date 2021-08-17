import java.util.ArrayList;
import java.util.Scanner;

class DukeBot {
    private final Scanner sc;
    private final ArrayList<String> taskList;

    public DukeBot(Scanner sc, ArrayList<String> taskList) {
        this.sc = sc;
        this.taskList = taskList;
    }

    public void start() {
        String greetings = "Hello! What can I do for you?\n";
        System.out.println(greetings);

        String cmd;
        while (true) {
            cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, taskList.get(i));
                }
            } else {
                System.out.println("added: " + cmd);
                taskList.add(cmd);
            }
            System.out.println();
        }
    }
}
