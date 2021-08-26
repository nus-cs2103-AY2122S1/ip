import java.io.BufferedWriter;
import java.io.FileWriter;

public class AddCommand extends Command {
    String command;
    public AddCommand(String command) {
        this.command = command;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if(command.split(" ")[0].equals("todo")) {
                if(command.split(" ", 2).length == 1) {
                    ToDo todo = new ToDo(command.split(" ", 2)[0]);
                } else {
                    ToDo todo = new ToDo (command.split(" ", 2)[1]);
                    tasks.addTask(todo);
                    ui.respondToTodo(tasks.getTasks(), todo);
                    storage.appendToFile(todo);
                }
            } else if(command.split(" ")[0].equals("deadline")) {
                if (command.split(" ", 2).length == 1) {
                    Deadline deadline = new Deadline(command.split(" ", 2)[0], "");
                } else {
                    String description = command.split(" ", 2)[1].split(" /")[0];
                    String by = command.split("/by ")[1];
                    Deadline deadline = new Deadline(description, by);
                    tasks.addTask(deadline);
                    ui.respondToDeadline(tasks.getTasks(), deadline);
                    storage.appendToFile(deadline);
                }
            } else {
                if (command.split(" ", 2).length == 1) {
                    Event event = new Event(command.split(" ", 2)[0], "");
                } else {
                    String description = command.split(" ", 2)[1].split(" /")[0];
                    String at = command.split("/at ")[1];
                    Event event = new Event(description, at);
                    tasks.addTask(event);
                    ui.respondToEvent(tasks.getTasks(), event);
                    storage.appendToFile(event);
                }
            }
        } catch (DukeException1 e) {
            ui.showError(e.getMessage());
        }

        //System.out.println("hi");;
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
