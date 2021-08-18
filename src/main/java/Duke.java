import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> tasks;

    public Duke() {
        this.tasks = new ArrayList<Task>(100);
    }

    private void start() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        // check userInput
        while (!userInput.equals("bye")) {
            String[] inputStringArray = userInput.split(" ", 2);
            switch (inputStringArray[0]) {
                case "list":
                    this.printTasks();
                    break;
                case "done":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task number not specified");
                        break;
                    }
                    try {
                        int taskIndex = Integer.parseInt(inputStringArray[1]) - 1;
                        Task doneTask = tasks.get(taskIndex);
                        doneTask.setDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + doneTask.toString());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Error: Invalid task number");
                    }
                    break;
                case "deadline":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task info not specified");
                        break;
                    }
                    String[] deadlineInfo = inputStringArray[1].split(" /by ", 2);
                    if (deadlineInfo.length < 2) {
                        System.out.println("Error: Invalid deadline task format");
                        break;
                    }
                    Task newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:\n  " + newDeadline.toString());
                    break;
                case "event":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task info not specified");
                        break;
                    }
                    String[] eventInfo = inputStringArray[1].split(" /at ", 2);
                    if (eventInfo.length < 2) {
                        System.out.println("Error: Invalid event task format");
                        break;
                    }
                    Task newEvent = new Event(eventInfo[0], eventInfo[1]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:\n" + "  " + newEvent.toString());
                    break;
                case "todo":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task info not specified");
                        break;
                    }
                    Task newToDo = new ToDo(inputStringArray[1]);
                    tasks.add(newToDo);
                    System.out.println("Got it. I've added this task:\n" + "  " + newToDo.toString());
                    break;
                default:
                    System.out.println("Error: Unknown command");
            }
            userInput = sc.nextLine(); // get new userInput
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printTasks() {
        System.out.println("Here are the tasks in your list:");
        int taskCount = tasks.size();
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

}
