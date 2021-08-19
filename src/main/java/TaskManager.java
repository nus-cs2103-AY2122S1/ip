import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private Scanner commandInput = null;
    private ArrayList<String> tasks = null;

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
            } else {
                this.add(command);
            }
        }
        commandInput.close();
    }

    private void add(String newTask) {
        tasks.add(newTask);
        Response.respond("added: " + newTask);
    }

    private void list() {
        Response.listResponse(tasks);
    }
}
