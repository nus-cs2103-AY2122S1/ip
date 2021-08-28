import java.util.ArrayList;

public class Parser {
    private Storage storage;
    private ArrayList<Task> list;
    private TaskList taskList;

    public Parser() {
        this.storage = new Storage();
        this.list = storage.loadFile();
        this.taskList = new TaskList(list, storage);
    }

    public void parse(String input) throws DukeException {
        String commandInput = input.split("\\s+")[0];
        Command command;
        switch (commandInput) {
            case "list":
                command = new ListCommand(taskList);
                command.execute();
                break;
            case "done":
                command = new DoneCommand(taskList, input);
                command.execute();
                break;
            case "todo":
                command = new TodoCommand(taskList, input);
                command.execute();
                break;
            case "deadline":
                command = new DeadlineCommand(taskList, input);
                command.execute();
                break;
            case "event":
                command = new EventCommand(taskList, input);
                command.execute();
                break;
            case "delete":
                command = new DeleteCommand(taskList, input);
                command.execute();
                break;
            case "bye":
                command = new ByeCommand(taskList);
                command.execute();
                break;
            default:
                throw new DukeException(Ui.unknownInputMsg());
        }

    }
}
