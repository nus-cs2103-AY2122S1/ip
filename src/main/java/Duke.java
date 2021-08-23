import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Duke {
    static String border = "--------------------------------------------------";

    public static void main(String[] args) {
        UI ui = new UI();

        Path storagePath = Paths.get(".","data","duke.txt");
        TaskArrayList taskList;
        try {
            taskList = Storage.load(storagePath);
        } catch (DukeException | IOException e){
            taskList = new TaskArrayList();
            ui.showLoadingError();
        }
        ui.displayLogo();
        ui.displayWelcome();

        Scanner userScanner = new Scanner(System.in);
        while (true){
            String userInput = userScanner.nextLine();
            // cmd_args splits the command from arguments
            // if no arguments, length will be 1
            // if arguments, length will be 2
            String[] cmd_args = userInput.split(" ",2);
            Task toAdd;

            // switch case to split by command
            switch (cmd_args[0]) {
                case ("bye"):
                    if (cmd_args.length > 1){
                        ui.display("☹ OOPS!!! bye can't take arguments.");
                        break;
                    }
                    ui.display("BYEEEEEE!\nHope to see you again soon :)");
                    Storage.dump(taskList,storagePath);
                    System.exit(0);
                    break;

                case ("list"):
                    if (cmd_args.length > 1){
                        ui.display("☹ OOPS!!! list can't take arguments.");
                        break;
                    }
                    ui.display(taskList.enumerate());
                    break;

                case ("done"):
                    try {
                        String reply = taskList.markDone(cmd_args);
                        ui.display(reply);
                    } catch (DukeException e) {
                        ui.display(e.toString());
                    }
                    break;

                case ("delete"):
                    try{
                        String reply = taskList.deleteTask(cmd_args);
                        ui.display(reply);
                    } catch (DukeException e){
                        ui.display(e.toString());
                    }
                    break;

                case ("todo"):
                    try {
                        toAdd = Todo.fromCmd(cmd_args);
                        ui.display(taskList.addTask(toAdd));
                    } catch (DukeException e){
                        ui.display(e.toString());
                    }
                    break;

                case ("deadline"):
                    try{
                        toAdd = Deadline.fromCmd(cmd_args);
                        ui.display(taskList.addTask(toAdd));
                    } catch (DukeException e){
                        ui.display(e.toString());
                    }
                    break;

                case ("event"):
                    try{
                        toAdd = Event.fromCmd(cmd_args);
                        ui.display(taskList.addTask(toAdd));
                    } catch (DukeException e) {
                        ui.display(e.toString());
                    }
                    break;

                default:
                    // unrecognised command
                    ui.display("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
