import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        // check userInput
        while (!userInput.equals("bye")) {
            String[] inputStringArray = userInput.split(" ", 2);
            switch (inputStringArray[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < 100; i++) {
                        Task task = tasks[i];
                        if (task == null) {
                            break;
                        } else {
                            System.out.println((i + 1) + ". " + task.toString());
                        }
                    }
                    break;
                case "done":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task number not specified");
                        break;
                    }
                    try {
                        int taskIndex = Integer.parseInt(inputStringArray[1]) - 1;
                        if (taskIndex < 0 || taskIndex > 99) {
                            System.out.println("Error: Please enter a valid task number (1 to 100)");
                            break;
                        }
                        Task doneTask = tasks[taskIndex];
                        if (doneTask == null) {
                            System.out.println("Error: Task does not exist");
                            break;
                        }
                        doneTask.setDone();
                        System.out.println("Nice! I've marked this task as done:\n" + doneTask.toString());
                    } catch (NumberFormatException e) {
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
                    tasks[taskCount] = newDeadline;
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + "  " + newDeadline.toString());
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
                    tasks[taskCount] = newEvent;
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + "  " + newEvent.toString());
                    break;
                case "todo":
                    if (inputStringArray.length < 2) {
                        System.out.println("Error: Task info not specified");
                        break;
                    }
                    Task newToDo = new ToDo(inputStringArray[1]);
                    tasks[taskCount] = newToDo;
                    taskCount++;
                    System.out.println("Got it. I've added this task:\n" + "  " + newToDo.toString());
                    break;
                default:
                    System.out.println("Error: Unknown command");
            }
            userInput = sc.nextLine(); // get new userInput
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

}
