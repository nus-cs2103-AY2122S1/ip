public class Logic {
    final private static String LINE = "     ____________________________________________________________\n";
//    final private static String INDENT = "      ";

    private static int numOfTasks = 0;
    private static Task[] taskList = new Task[100];
    private static boolean continueChat = true;

    public static void takeInput(String input) {
        if (input.equals("bye")) {
            formatOutput("Bye. Hope to see you again soon!");
            continueChat = false;
        } else if (input.equals("list")) {
            renderList();
        } else if (getFirstWord(input).equals("done")) {
            markTaskComplete(input);
        } else {
            addTask(input);

        }
    }

    public static boolean endChat() {
        return !continueChat;
    }

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');
        if (index > -1) { // Check if there is more than one word.
            return text.substring(0, index).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }

    private static int getTaskNumber(String cmd) {
        String[] result = cmd.split(" ");
        if (result[1].matches("\\d+")) {
            return Integer.parseInt(result[1]);
        }
        return -1;
    }

    private static void markTaskComplete(String cmd) {
        int index = getTaskNumber(cmd) - 1;
        if (index < 0 || index > numOfTasks - 1) {
            formatOutput("Input is invalid!");
            return;
        }
        Task task = taskList[index];
        task.markDone();
        formatOutput("Nice! I've marked this task as done: \n" + task.toString());
    }

    private static void addTask(String task) {
        taskList[numOfTasks] = new Task(task);
        numOfTasks++;
        formatOutput("added: " + task);
    }

    private static void renderList() {
        String op = "";
        for (int i = 0; i < numOfTasks; i++) {
            op = op + (i + 1) + ". " + taskList[i].toString() + "\n";
        }
        formatOutput("Here are the tasks in your list:\n" + op);
    }

    private static void formatOutput(String op) {
        String output = op;
        System.out.println(LINE);
        output.lines().forEach(line -> System.out.println("      " + line));
        System.out.println(LINE);
    }

}
