import java.util.ArrayList;
import java.util.List;

/**
 *  This class handles user commands when keyed onto the terminal
 * @author Ryan Tian Jun
 */
public class FeatureMain {
    private String currentCommand;
    private static List<Task> commands = new ArrayList<Task>();
    private int taskNumber;

    public FeatureMain(Task task) {
        String command = task.description;
        if (isList(command.toLowerCase())) {
            this.currentCommand = command.toLowerCase();
        } else if (isDone(command.toLowerCase())){
            this.currentCommand = command.toLowerCase();
        } else {
            this.currentCommand = command.toLowerCase();
            FeatureMain.commands.add(task);
        }
    }

    /**
     * Processes user commands to return a specific action
     *
     * @return Specific feature pertaining to command
     */
    public String processCommand() {
        if (isList(this.currentCommand)) {
            return userCommands();
        } else if (isDone(this.currentCommand)) {
            return markDone();
        } else {
            return addCommand();
        }
    }

    // checks if command given is a list
    private boolean isList(String command) {
        return command.equals("list");
    }

    //checks if command given is done, also adds task mumber if valid
    private boolean isDone(String command) {
        if (command.length() > 4) {
            if (command.substring(0, 4).equals("done") &&
                    isNumeric(command.substring(5))) {
                taskNumber = Integer.parseInt(command.substring(5));
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    // checks if String is numeric
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Echos user commands
     *
     * @return returns an echo of the users commands
     */
    private String echoCommand() {
        System.out.println(currentCommand);
        newLine();
        return currentCommand;
    }

    /**
     * Adds user commands to history ArrayList commands
     *
     * @return Notifies the user that their command has been added
     */
    private String addCommand() {
        System.out.println("added: " + currentCommand);
        newLine();
        return "added: " + currentCommand;
    }

    /**
     * Prints past commands
     *
     * @return returns a printout of all the past user commands
     */
    private static String userCommands() {
        int count = 1;
        for (Task item : commands) {
            System.out.println(count + ". " + "[ " + item.getStatusIcon() + " ] "+ item.getDescription());
            count++;
        }
        newLine();
        return commands.toString();
    }

    private String markDone() {
        if (taskNumber <= commands.size()) {
            System.out.println("Nice! I've marked this task as done: ");

            Task taskToChange = commands.get(taskNumber - 1);
            taskToChange.markAsDone();

            System.out.println("   " + "[ " + taskToChange.getStatusIcon() + " ] " + " " + taskToChange.getDescription());
            newLine();
            return "   " + taskToChange.getStatusIcon() + " " + taskToChange.getDescription();
        } else {
            System.out.println("Task does not exist");
            newLine();
            return "Task does not exist";
        }


    }

    // Simply creates a new line in the terminal
    private static String newLine() {
        System.out.println("\n");
        return "\n";
    }
}
