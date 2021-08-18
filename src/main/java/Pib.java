import java.util.ArrayList;
import java.util.Scanner;
import Tasks.Task;
import Tasks.Todo;
import Tasks.Deadline;
import Tasks.Event;

public class Pib {
    public static String DIVIDER = "____________________________________________________________\n";
    private ArrayList<Task> list;
    private Scanner sc;

    public Pib() {
        System.out.println(DIVIDER + "Hello! I'm Pib\n" + "Tell me something!\n" + DIVIDER);
        list = new ArrayList<>();
        sc = new Scanner(System.in);
        readInput();
    }

    private enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private void readInput() {
        while (sc.hasNextLine()) {
            System.out.println(DIVIDER);
            String next = sc.nextLine();

            if (next.contains(" ")) {
                int spaceDividerIndex = next.indexOf(" ");
                String taskType = next.substring(0, spaceDividerIndex).toLowerCase();
                String taskDetails = next.substring(1 + spaceDividerIndex);
                switch (taskType) {
                    case "todo":
                        addToList(TaskType.TODO, taskDetails);
                        break;
                    case "deadline": {
                        addToList(TaskType.DEADLINE, taskDetails);
                        break;
                    }
                    case "event": {
                        addToList(TaskType.EVENT, taskDetails);
                        break;
                    }
                    case "done": {
                        markAsDone(taskDetails);
                        break;
                    }
                    default: {
                        printUnknownCmd();
                        break;
                    }
                }
            } else {
                String action = next.toLowerCase();
                if (action.equals("list")) {
                    displayList();
                } else if (action.equals("bye")) {
                    endPib();
                    break;
                } else {
                    printUnknownCmd();
                }
            }
            System.out.println(DIVIDER);
        }
    }

    private void printUnknownCmd() {
        System.out.println("Uh oh! I don't know that command :(\n");
    }

    private void endPib() {
        System.out.println("Bye! See you next time!\n");
        System.out.println(DIVIDER);
        sc.close();
    }

    private void addToList(TaskType t, String taskDetails) {
        if (t.equals(TaskType.TODO)) {
            list.add(new Todo(taskDetails));
        } else if (t.equals(TaskType.DEADLINE)) {
            int dateDividerIndex = taskDetails.indexOf("/by ");
            list.add(new Deadline(taskDetails.substring(0, dateDividerIndex),
                    taskDetails.substring(dateDividerIndex + 4)));
        } else if (t.equals(TaskType.EVENT)) {
            int dateDividerIndex = taskDetails.indexOf("/at ");
            list.add(new Event(taskDetails.substring(0, dateDividerIndex),
                    taskDetails.substring(dateDividerIndex + 4)));
        }
        System.out.println("Now you have " + list.size() + " task(s) in your list.\n");
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println("Nothing added yet\n");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i).toString());
            }
            System.out.println("");
        }
    }

    private void markAsDone(String s) {
        if (s.isBlank()) {
            System.out.println("Tell me which item to mark as complete!\n ");
        } else {
            list.get(Integer.parseInt(s) - 1).markAsDone();
        }
    }

    public static void main(String[] args) {
        Pib p = new Pib();
    }
}
