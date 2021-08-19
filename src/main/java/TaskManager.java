import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskManager {
    private Scanner commandInput;
    private ArrayList<Task> tasks;

    public TaskManager(Scanner commandInput) {
        this.commandInput = commandInput;
        this.tasks = new ArrayList<>();
        this.listen();
    }

    private void listen() {
        while (commandInput.hasNextLine()) {
            String command = commandInput.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                Response.exitResponse();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                this.list();
            } else if (Pattern.compile("done\\s\\d+").matcher(command.toLowerCase()).matches()) {
                this.markTaskDone(command.substring(5));
            } else {
                this.add(command);
            }
        }
        commandInput.close();
    }

    private void add(String newTaskDesc) {
        Task newTask = new Task(newTaskDesc);
        tasks.add(newTask);
        Response.respond("added: " + newTaskDesc);
    }

    private void markTaskDone(String index) {
        int indexInt = Integer.parseInt(index) - 1;
        if (indexInt < tasks.size() && indexInt > -1) {
            Task currTask = tasks.get(indexInt);
            currTask.markDone();
            Response.respond("Nice! I've marked this task as done:\n"
                    + "[X] " + currTask.description());
        } else {
            Response.respond("That task doesn't exist.\nTry again.");
        }

    }

    private void list() {
        Response.listResponse(tasks);
    }
}
