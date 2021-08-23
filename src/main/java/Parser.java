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


    public void run(String userInput) throws DukeException{
        String[] cmd_args = userInput.split(" ",2);
        Task toAdd;

        // switch case to split by command
        switch (cmd_args[0]) {
        case ("bye"):
            if (cmd_args.length > 1){
                throw new DukeException("command bye takes no arguments.");
            }
            ui.display("BYEEEEEE!\nHope to see you again soon :)");
            System.exit(0);
            break;

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
            ui.display(taskList.deleteTask(cmd_args));
            break;

        case ("todo"):
            toAdd = Todo.fromCmd(cmd_args);
            ui.display(taskList.addTask(toAdd));
            break;

        case ("deadline"):
            toAdd = Deadline.fromCmd(cmd_args);
            ui.display(taskList.addTask(toAdd));
            break;

        case ("event"):
            toAdd = Event.fromCmd(cmd_args);
            ui.display(taskList.addTask(toAdd));
            break;

        default:
            // unrecognised command
            throw new DukeException("Unrecognised command");
        }
    }
}
