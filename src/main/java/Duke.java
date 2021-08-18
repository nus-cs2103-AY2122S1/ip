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
            try {
                switch (inputStringArray[0]) {
                    case "list":
                        this.printTasks();
                        break;
                    case "done":
                        if (inputStringArray.length < 2) {
                            throw new DukeException("Please specify a task number.");
                        }
                        try {
                            int taskIndex = Integer.parseInt(inputStringArray[1]) - 1;
                            Task doneTask = tasks.get(taskIndex);
                            doneTask.setDone();
                            System.out.println("Nice! I've marked this task as done:\n  " + doneTask.toString());
                            break;
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            throw new DukeException("Please specify a valid task number.");
                        }
                    case "deadline":
                        if (inputStringArray.length < 2) {
                            throw new DukeException("Please specify the task info.");
                        }
                        String[] deadlineInfo = inputStringArray[1].split(" /by ", 2);
                        if (deadlineInfo.length < 2) {
                            throw new DukeException("Please enter a valid deadline format.");
                        }
                        Task newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task:\n  " + newDeadline.toString());
                        break;
                    case "event":
                        if (inputStringArray.length < 2) {
                            throw new DukeException("Please specify the task info.");
                        }
                        String[] eventInfo = inputStringArray[1].split(" /at ", 2);
                        if (eventInfo.length < 2) {
                            throw new DukeException("Please enter a valid event format.");
                        }
                        Task newEvent = new Event(eventInfo[0], eventInfo[1]);
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task:\n" + "  " + newEvent.toString());
                        break;
                    case "todo":
                        if (inputStringArray.length < 2) {
                            throw new DukeException("Please specify the task info.");
                        }
                        Task newToDo = new ToDo(inputStringArray[1]);
                        tasks.add(newToDo);
                        System.out.println("Got it. I've added this task:\n" + "  " + newToDo.toString());
                        break;
                    default:
                        throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
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
