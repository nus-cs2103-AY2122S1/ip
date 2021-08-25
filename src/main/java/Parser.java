public class Parser {
    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public String[] parseCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            // Rmbr to add exit condition to parent function
            return Ui.BYE_MSG;
        } else if (command.equals("list")){
            return tasks.getTaskStrings();
        } else if (command.startsWith("done ")) {
            return tasks.markTask(Integer.parseInt(command.substring(5)) - 1);
        } else if (command.startsWith("delete ")) {
            return tasks.deleteTask(Integer.parseInt(command.substring(7)) - 1);
        } else if (command.startsWith("todo")) {
            if (command.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return tasks.addTask(new Todo(command.substring(5)));
        } else if (command.startsWith("deadline")) {
            if (command.length() < 10) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            int byIndex = command.indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("/by not found.");
            }
            return tasks.addTask(new Deadline(
                command.substring(9, byIndex - 1),
                command.substring(byIndex + 4)));
        } else if (command.startsWith("event")) {
            if (command.length() < 7) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            int atIndex = command.indexOf("/at");
            if (atIndex == -1) {
                throw new DukeException("/at not found.");
            }
            return tasks.addTask(new Event(
                command.substring(6, atIndex - 1),
                command.substring(atIndex + 4)));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
