import java.util.Scanner;
public class Duke {
    private static final String BYE_COMMAND = "Bye. Hope to see you again soon!";
    private static final String GREETING_COMMAND = 
    "Hello! I'm Duke" + "\n" + "What can I do for you?";

    public static void main(String[] args) {
        System.out.println(GREETING_COMMAND);
        runDukeBot();
        System.out.println(BYE_COMMAND);
    }

    private static void runDukeBot() {
        Scanner sc = new Scanner(System.in);
        TasksHandler handler = new TasksHandler();
        while (sc.hasNextLine()) {
            String[] instructions = sc.nextLine().split(" ");
            if (handler.addAndDisplayTasks(instructions)) {
                break;
            };
        }
        sc.close();
    }
}
