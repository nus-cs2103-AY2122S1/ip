import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        label:
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
                case "bye":
                    String closingMessage = "Goodbye! Hope to see you again soon!\n";
                    System.out.println(outputTemplate(closingMessage));
                    sc.close();
                    break label;
                case "list":
                    String list = "These are the tasks in your list: \n";
                    for (int i = 0; i < taskList.size(); i++) {
                        list += (i + 1) + ". " + taskList.get(i).toString() + "\n";
                    }
                    System.out.println(outputTemplate(list));
                    break;
                case "done": {
                    int taskNumber = sc.nextInt();
                    Task completedTask = taskList.get(taskNumber - 1);
                    completedTask.markAsDone();
                    String message = "Good work! Task is now marked as done: \n" + completedTask + "\n";
                    System.out.println(outputTemplate(message));
                    break;
                }
                case "todo": {
                    String description = sc.nextLine();
                    ToDo task = new ToDo(description.trim());
                    taskList.add(task);
                    String message = "Alright! New task added: \n" + task +
                            String.format("\nThere are now %d tasks in your list \n", taskList.size());
                    System.out.println(outputTemplate(message));
                    break;
                }
                case "deadline": {
                    String description = sc.nextLine();
                    String[] parameters = description.split(" /by ");
                    Deadline task = new Deadline(parameters[0].trim(), parameters[1]);
                    taskList.add(task);
                    String message = "Alright! New task added: \n" + task +
                            String.format("\nThere are now %d tasks in your list\n", taskList.size());
                    System.out.println(outputTemplate(message));
                    break;
                }
                case "event": {
                    String description = sc.nextLine();
                    String[] parameters = description.split(" /at ");
                    Event task = new Event(parameters[0].trim(), parameters[1]);
                    taskList.add(task);
                    String message = "Alright! New task added: \n" + task +
                            String.format("\nThere are now %d tasks in your list\n", taskList.size());
                    System.out.println(outputTemplate(message));
                    break;
                }
                default: {
                    String description = command + sc.nextLine();
                    taskList.add(new Task(description));
                    System.out.println(outputTemplate(description + "\n"));
                    break;
                }
            }
        }
    }

    private static String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + line;
    }
}
