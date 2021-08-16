import java.util.ArrayList;
import java.util.List;

/**
 *  This class handles user commands when keyed onto the terminal
 * @author Ryan Tian Jun
 */
public class FeatureMain {
    private String currentCommand;
    protected static List<Task> commands = new ArrayList<Task>();
    private int taskNumber;

    public FeatureMain(String command) {
        try {
            if (isList(command)) {
                this.currentCommand = command;
                userCommands();
            } else if (isDone(command)) {
                this.currentCommand = command;
                markDone();
            } else if (isToDo(command)) {
                this.currentCommand = command;
                Task task = new ToDo(command.substring(5), Task.TYPE.T);
                FeatureMain.commands.add(task);
                printTask(task);
            } else if (isDeadLine(command)) {
                this.currentCommand = command;
                Task task = new DeadLine(command.substring(9), Task.TYPE.D, returnDeadline(command));
                FeatureMain.commands.add(task);
                printTask(task);
            } else if (isEvent(command)) {
                this.currentCommand = command;
                Task task = new Event(command.substring(6), Task.TYPE.E, returnTimeline(command));
                FeatureMain.commands.add(task);
                printTask(task);
            } else {
                this.currentCommand = command;
                // Unknown Command, throw error
                throw new DukeException("Error, Invalid Command");
            }
        } catch (DukeException e) {
            System.out.println(e + "\n");
        }

    }


    // checks if command given is a list
    private boolean isList(String command) {
        return command.equals("list");
    }

    //checks if command given is done, also adds task number if valid
    private boolean isDone(String command) throws DukeException {
        if (command.length() >= 4 && command.startsWith("done")) {
            if (command.length() == 4)  {
                throw new DukeException("The Done command must be followed by a number!");
            } else {
                if (isNumeric(command.substring(5))) {
                    taskNumber = Integer.parseInt(command.substring(5));
                    return true;
                } else {
                    throw new DukeException("The Done command requires a number, separated by whitespace!");
                }
            }
        } else {
            return false;
        }
    }

    private boolean isToDo(String command) throws DukeException {
        if (command.length() >= 4 && command.startsWith("todo")) {
            if (command.length() == 4) {
                throw new DukeException("ToDo", 1);
            } else if (command.charAt(4) != ' ') {
                throw new DukeException("ToDo", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isDeadLine(String command) throws DukeException{
        if (command.length() >= 8 && command.startsWith("deadline")) {
            if (command.length() == 8) {
                throw new DukeException("Deadline", 1);
            } else if (command.charAt(8) != ' ') {
                throw new DukeException("Deadline", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean isEvent(String command) throws DukeException {
        if (command.length() >= 5 && command.startsWith("event")) {
            if (command.length() == 5) {
                throw new DukeException("Event", 1);
            } else if (command.charAt(5) != ' '){
                throw new DukeException("Event", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    private String returnDeadline(String command) {
        int startNumber = command.indexOf("/by");

        if (startNumber > 0 && command.length() > 3) {
            return command.substring(startNumber + 3);
        } else {
            return "no Deadline";
        }
    }

    private String returnTimeline(String command) {
        int startNumber = command.indexOf("/at");

        if (startNumber > 0 && command.length() > 3) {
            return command.substring(startNumber + 3);
        } else {
            return "no Timeline";
        }
    }


    // checks if String is numeric
    private static boolean isNumeric(String str) {
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
            System.out.println(count + ". "  + item);
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

            System.out.println("   " + taskToChange);
            newLine();
            return "   " + taskToChange;
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

    // prints addition of task
    private void printTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + FeatureMain.commands.size() + " tasks in the list.\n");
    }

}
