public class Logic {
    final private static String LINE = "     ____________________________________________________________\n";
    final private static String INDENT = "      ";

    private static int numOfTasks = 0;
    private static String[] taskList = new String[100];
    private static boolean continueChat = true;

    public static void takeInput(String input) {
        if (input.equals("bye")) {
            formatOutput("Bye. Hope to see you again soon!");
            continueChat = false;
        } else if (input.equals("list")) {
            renderList();
        } else {
            addTask(input);
        }
    }

    public static boolean endChat() {
        return !continueChat;
    }

    private static void addTask(String task) {
        taskList[numOfTasks] = task;
        numOfTasks++;
        formatOutput("added: " + task);
    }

    private static void renderList() {
        String op = "";
        for (int i = 0; i < numOfTasks; i++) {
            op = op + (i + 1) + ". " + taskList[i] + "\n";
        }
        formatOutput(op);
    }

    private static void formatOutput(String op) {
        String output = op;
        System.out.println(LINE);
        output.lines().forEach(line -> System.out.println("      " + line));
        System.out.println(LINE);
    }

}
