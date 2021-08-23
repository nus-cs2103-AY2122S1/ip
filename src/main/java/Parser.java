import java.nio.file.Path;

/**
 * Parser to parse user commands
 */
public class Parser {
    UI ui;
    TaskArrayList taskList;
    Path storagePath;

    Parser(UI ui,TaskArrayList taskList,Path storagePath){
        this.ui = ui;
        this.taskList = taskList;
        this.storagePath = storagePath;
    }

    /**
     * Run a user input string
     * @param userInput String keyed in by user
     * @return isExit, true if duke should exit after this command
     * @throws DukeException
     */
    public boolean run(String userInput) throws DukeException{
        String[] cmd_args = userInput.split(" ",2);
        Task toAdd;

        // switch case to split by command
        switch (cmd_args[0]) {
        case ("bye"):
            if (cmd_args.length > 1){
                throw new DukeException("command bye takes no arguments.");
            }
            ui.display("BYEEEEEE!\nHope to see you again soon :)");
            return true;

        case ("list"):
            if (cmd_args.length > 1){
                throw new DukeException("command list takes no arguments.");
            }
            ui.display(taskList.enumerate());
            break;

        case ("done"):
            ui.display(taskList.markDone(cmd_args));
            break;

        case ("delete"):
            if (cmd_args.length != 2){
                throw new DukeException(TaskArrayList.DELETE_USAGE_TEXT);
            }
            if (!cmd_args[1].matches("[0-9]+")){
                throw new DukeException(TaskArrayList.DELETE_USAGE_TEXT);
            }
            ui.display(taskList.deleteTask(Integer.parseInt(cmd_args[1])));
            break;

        case ("todo"):
            if (cmd_args.length != 2 ){
                throw new DukeException(Todo.USAGE_TEXT);
            }
            toAdd = new Todo(cmd_args[1]);
            ui.display(taskList.addTask(toAdd));
            break;

        case ("deadline"):
            if (cmd_args.length != 2 ){
                throw new DukeException (Deadline.USAGE_TEXT);
            }
            String[] name_by = cmd_args[1].split("/by",2);
            if (name_by.length != 2){
                throw new DukeException (Deadline.USAGE_TEXT);
            }
            toAdd = new Deadline(name_by[0],name_by[1]);
            ui.display(taskList.addTask(toAdd));
            break;

        case ("event"):
            if (cmd_args.length != 2 ){
                throw new DukeException (Event.USAGE_TEXT);
            }
            String[] name_at = cmd_args[1].split("/at",2);
            if (name_at.length != 2){
                throw new DukeException (Event.USAGE_TEXT);
            }
            toAdd = new Event(name_at[0],name_at[1]);
            ui.display(taskList.addTask(toAdd));
            break;

        default:
            throw new DukeException("Unrecognised command");
        }
        return false;
    }
}
