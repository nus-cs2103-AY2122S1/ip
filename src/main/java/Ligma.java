import java.util.Scanner;

public class Ligma {

    private static final String PARTITION = "______________________";
    private enum TaskType {
        TODO, EVENT, DEADLINE
    }
    private static Task[] tasks;
    private static int noOfTasks;

    private static boolean response(String command) {
        //returns true if bot should exit, i.e. command is bye
        if (command.equals("bye")) {
            System.out.println(PARTITION
                    + "\n Bye. Hope you like Imagine Dragons.\n"
                    + PARTITION);
            return true;
        }
        if (command.equals("list")) {
            list();
        } else {
            int splitInd = command.indexOf(' ');
            String action = command.substring(0, splitInd);
            String description = command.substring(splitInd + 1);
            switch (action) {
                case "todo":
                    addTask(TaskType.TODO, description);
                    break;
                case "event":
                    addTask(TaskType.EVENT, description);
                    break;
                case "deadline":
                    addTask(TaskType.DEADLINE, description);
                    break;
                case "done":
                    markTaskDone(Character
                            .getNumericValue(command.charAt(splitInd + 1)) - 1);
                    break;
            }
        }
        return false;
    }

    private static void list() {
        System.out.println(PARTITION + '\n');
        for (int i = 0; i <= 100 && tasks[i] != null; i++) {
            System.out.println((i+1) + ". " + tasks[i]);
        }
        System.out.println('\n' + PARTITION);
    }

    private static void addTask(TaskType t, String desc) {
        Task task = t == TaskType.TODO
                ? new Todo(desc)
                : t == TaskType.DEADLINE
                    ? Deadline.createDeadline(desc)
                    : Event.createEvent(desc);
        tasks[noOfTasks] = task;
        noOfTasks++;
        System.out.println(PARTITION
                + "\n Added: " + task
                + String.format("\n You now have %d tasks. \n", noOfTasks)
                + PARTITION);
    }

    private static void markTaskDone(int taskNo) {
        tasks[taskNo].markAsDone();
        System.out.println(PARTITION + "\n Successfully marked as done: \n"
                + tasks[taskNo] + "\n" + PARTITION);
    }

    public static void main(String[] args) {
        System.out.println(PARTITION
                + "\n Hello! I'm Ligma."
                + "\n What can I do for you? \n" + PARTITION + "\n");

        tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (response(sc.nextLine())) break;
        }
        sc.close();
    }
}
