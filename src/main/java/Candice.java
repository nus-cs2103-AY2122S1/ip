import java.util.Scanner;

public class Candice {

    private static final String PARTITION = "______________________";
    private static Task[] tasks;
    private static int noOfTasks;

    private static boolean response(String command) {
        //returns true if bot should exit, i.e. command is bye
        if (command.equalsIgnoreCase("bye")) {
            System.out.println(PARTITION
                    + "\n Bye. Hope to see you again soon! \n"
                    + PARTITION);
            return true;
        } else if (command.equalsIgnoreCase("list")) {
            list();
            return false;
        } else if (command
                .substring(0, 5)
                .equalsIgnoreCase("done ")) {
            markTaskDone(Character.getNumericValue(command.charAt(5)) - 1);
            return false;
        } else {
            addTask(command);
            return false;
        }
    }

    private static void list() {
        System.out.println(PARTITION + '\n');
        for (int i = 0; i <= 100 && tasks[i] != null; i++) {
            System.out.println((i+1) + ". " + tasks[i]);
        }
        System.out.println('\n' + PARTITION);
    }

    private static void addTask(String command) {
        tasks[noOfTasks] = new Task(command);
        noOfTasks++;
        System.out.println(PARTITION + "\n added: "
                + command + "\n" + PARTITION);
    }

    private static void markTaskDone(int taskNo) {
        tasks[taskNo].markAsDone();
        System.out.println(PARTITION + "\n Successfully marked as done: \n"
                + tasks[taskNo] + "\n" + PARTITION);
    }

    public static void main(String[] args) {
        System.out.println(PARTITION
                + "\n Hello! I'm Candice. The nickname's Nuts." +
                "\n Last name Feet-Inn, " +
                "but you can call me your MeowWoof."
                + "\n What can I do for you? \n" + PARTITION + "\n");

        tasks = new Task[100];

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            if (response(sc.nextLine())) break;
        }
        sc.close();
    }
}
