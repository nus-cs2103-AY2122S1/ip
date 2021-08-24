import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    
    protected static boolean canLog = false;
    protected static final String logPath = "data/duke.txt";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyList taskList = new MyList();
       
        File previousLog = new File(logPath);
        try {
            if (! previousLog.createNewFile()) { // file already exists
                taskList.importPreviousTasks(previousLog);
                printMessage("Successfully established connection with file " + Duke.logPath
                        + "\nPrevious task log imported."
                        + "\nAll changes to task log will be saved there.");
            } else { // new file is created
                printMessage("Specified file not found.\nNew file created: " + Duke.logPath
                        + "\nTask log will be saved there.");
            }
            Duke.canLog = true;
        } catch (IOException ex) {
            printMessage("Unable to create/open specified file.\nTasks will not be logged.");
        }
        
        String welcomeMessage = "Hello I'm Duke!\nWhat can I do for you?";
        printMessage(welcomeMessage);

        boolean isCompleted = false;
        while (! isCompleted) {
            String command = sc.nextLine();
            String[] commandTokens = command.split(" ");
            // parse command
            try {
                switch (commandTokens[0]) {
                    case "bye":
                        isCompleted = true;
                        break;
                    case "done":
                        printMessage(taskList.markAsCompleted(command.substring(5).trim()));
                        break;
                    case "list":
                        printMessage(taskList.getAllTasks());
                        break;
                    case "delete":
                        printMessage(taskList.deleteItem(command.substring(7)));
                        break;
                    default:
                        try {
                            printMessage(taskList.addItem(command));
                        } catch (DukeException ex) {
                            printMessage(ex.getMessage());
                        }
                }
            } catch (ArrayIndexOutOfBoundsException ex) { // only occurs when the user only types whitespace
                printMessage("Please type some commands!");
            } catch (DukeException.InvalidTaskNumException ex) {
                printMessage(ex.getMessage());
            } catch (IOException ex) {
                printMessage("Unable to log task. Task logging will stop");
                Duke.canLog = false;
            } catch (DateTimeParseException ex) {
                printMessage("Invalid task description: "
                        + "invalid date/time\nPlease use [command type] [task name] / [dd-mm-yyyy] [time (in 24hr " +
                        "format)" +
                        "]\ne.g. event lecture / 21-02-2021 1500");
            }
        }
        printMessage("Goodbye for now!");
    }

    private static void printMessage(String message) {
        System.out.println("-------------------------");
        System.out.println(message);
        System.out.println("-------------------------");
    }
}
