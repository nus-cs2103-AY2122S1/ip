import java.util.ArrayList;

public class ToDoCommand extends Command {
    public ToDoCommand() {
        super("todo");
    }

    /**
     * The execute() method in ToDoCommand inputs a ToDo task into the Duke chat-bot.
     *
     * @param des the user input into the Duke chat-box.
     * @throws DukeException if input is not correctly formatted with task argument.
     */
    public void execute(String des, TaskList tList) throws DukeException {
        if (des.equals("todo")) {
            throw new DukeException("\"todo\" command not correctly formatted \nPlease insert task argument");
        }
        String description = des.substring(5);
        ToDo atHand = new ToDo(description);
        tList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(atHand);
        this.numberOfTasks(tList);

        Storage.createFile();
        Storage.writeToFile(tList);
    }
}
