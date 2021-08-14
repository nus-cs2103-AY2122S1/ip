import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation for Duke.
 *
 * @author Wong Yun Rui Chris
 */
public class Duke {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * A private method to add the new Task into list and return the corresponding String reply.
     *
     * @param task The Task that will be added into the list
     * @return The String of the reply after adding a task
     */
    private String addTask(Task task) {
        this.list.add(task);
        return "\tGot it. I've added this task:\n\t\t " + task.toString()
                + "\n\tNow you have " + list.size() + " tasks in the list.";
    }

    /**
     * A private method to return the list of all the Tasks.
     *
     * @return The String of the list of all the tasks formatted
     */
    private String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: list) {
            String temp = "\t" + i + "." + task.toString() + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }

    /**
     * A private method to mark the Task at the index of the list to be done.
     *
     * @param index The index of the Task in the list that is to be mark as done
     * @return The corresponding String reply after marking a task done
     */
    private String markTask(int index) {
        return "\tNice! I've marked this task as done:\n\t\t" + list.get(index - 1).markDone();
    }

    /**
     * The method to handle all the text input to call the correct corresponding method
     *
     * @param input The text input from the user to Duke
     * @return The corresponding String reply to the user's input
     */
    public String handleInput(String input) throws  DukeException {
        String[] inputArray = input.split(" ",2);
        String reply = "";
        String[] tempArray;

        switch (inputArray[0]) {
            case "list":
                reply = this.displayTask();
                break;

            case "done":
                reply = this.markTask(Integer.parseInt(inputArray[1]));
                break;

            case "todo":
                if (inputArray.length < 2 || inputArray[1].isBlank()) {
                    throw new DukeException("The description of todo cannot be empty");
                }
                reply = this.addTask(new Todo(inputArray[1]));
                break;

            case "deadline":
                if (inputArray.length < 2 || inputArray[1].isBlank()) {
                    throw new DukeException("The description of deadline cannot be empty");
                }

                tempArray = inputArray[1].split(" /by ");
                if (tempArray.length < 2) {
                    throw new DukeException("The format for deadline is wrong");
                } else if(tempArray[0].isBlank()) {
                    throw new DukeException("The description of deadline cannot be empty");
                } else if(tempArray[1].isBlank()) {
                    throw new DukeException("The date/time is missing from deadline");
                }
                reply = this.addTask(new Deadline(tempArray[0], tempArray[1]));
                break;

            case "event":
                if (inputArray.length < 2 || inputArray[1].isBlank()) {
                    throw new DukeException("The description of event cannot be empty");
                }

                tempArray = inputArray[1].split(" /at ");
                if (tempArray.length < 2) {
                    throw new DukeException("The format for event is wrong");
                } else if(tempArray[0].isBlank()) {
                    throw new DukeException("The description of event cannot be empty");
                } else if(tempArray[1].isBlank()) {
                    throw new DukeException("The date/time is missing from event");
                }
                reply = this.addTask(new Event(tempArray[0], tempArray[1]));
                break;

            default:
                throw new DukeException("I'm sorry, but I don't know what that means");
        }
        return reply;
    }
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you\n");

        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            try {
                System.out.println(duke.handleInput(input));
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
            input = sc.nextLine();
        }

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
