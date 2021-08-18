import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private Scanner sc;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void chat() {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            try {
                String command = sc.next();
                switch (command) {
                    case "bye":
                        String closingMessage = "Goodbye! Hope to see you again soon!\n";
                        System.out.println(outputTemplate(closingMessage));
                        sc.close();
                        break label;
                    case "list":
                        String list = "These are the tasks in your list:\n";
                        for (int i = 0; i < taskList.size(); i++) {
                            list += (i + 1) + ". " + taskList.get(i).toString() + "\n";
                        }
                        System.out.println(outputTemplate(list));
                        break;
                    case "done": {
                        int taskNumber = getTaskNumber();
                        Task completedTask = taskList.get(taskNumber - 1);
                        completedTask.markAsDone();
                        String message = "Good work! Task is now marked as done:\n" + completedTask + "\n";
                        System.out.println(outputTemplate(message));
                        break;
                    }
                    case "todo": {
                        String description = getDescription("todo");
                        ToDo task = new ToDo(description.trim());
                        taskList.add(task);
                        String message = "Alright! New task added:\n" + task +
                                String.format("\nThere are now %d tasks in your list\n", taskList.size());
                        System.out.println(outputTemplate(message));
                        break;
                    }
                    case "deadline": {
                        String description = getDescription("deadline");
                        String[] parameters = description.split(" /by ");
                        Deadline task = new Deadline(parameters[0].trim(), parameters[1]);
                        taskList.add(task);
                        String message = "Alright! New task added:\n" + task +
                                String.format("\nThere are now %d tasks in your list\n", taskList.size());
                        System.out.println(outputTemplate(message));
                        break;
                    }
                    case "event": {
                        String description = getDescription("event");
                        String[] parameters = description.split(" /at ");
                        Event task = new Event(parameters[0], parameters[1]);
                        taskList.add(task);
                        String message = "Alright! New task added:\n" + task +
                                String.format("\nThere are now %d tasks in your list\n", taskList.size());
                        System.out.println(outputTemplate(message));
                        break;
                    }
                    default: {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(outputTemplate(e + "\n"));
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }

    private String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + line;
    }

    private String getDescription(String taskType) throws DukeException {
        String description = sc.nextLine().trim();
        if (description.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be empty!", taskType));
        } else {
            return description;
        }
    }

    private int getTaskNumber() throws DukeException {
        int taskNumber = sc.nextInt();
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new DukeException("I cannot find this task number!");
        } else {
            return taskNumber;
        }
    }
}
