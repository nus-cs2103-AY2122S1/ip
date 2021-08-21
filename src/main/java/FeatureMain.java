import java.util.ArrayList;
import java.util.List;

/**
 *  This class handles user commands when keyed onto the terminal
 *
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
            } else if (isDelete(command)) {
                this.currentCommand = command;
                deleteTask();
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

    /**
     * Checks if it is a 'Done' command
     *
     * @throws DukeException Either a syntax error or lack of number provided
     * @return returns true if it is said command
     */
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

    /**
     * Checks if it is a 'Todo' command
     *
     * @throws DukeException Either a syntax error or lack of description
     * @return returns true if it is said command
     */
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

    /**
     * Checks if it is a 'Deadline' command
     *
     * @throws DukeException Either a syntax error or lack of description
     * @return returns true if it is said command
     */
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

    /**
     * Checks if it is an 'Event' command
     *
     * @throws DukeException Either a syntax error or lack of description
     * @return returns true if it is said command
     */
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

    /**
     * Checks if it is a 'Delete' command
     *
     * @throws DukeException -
     * @return returns true if it is said command
     */
    private boolean isDelete(String command) throws DukeException {
        if (command.length() >= 6 && command.startsWith("delete")) {
            if (command.length() == 6)  {
                throw new DukeException("The Delete command must be followed by a number!");
            } else {
                  if (command.charAt(6) != ' ') {
                      throw new DukeException("The Delete command requires a number, separated by whitespace!");
                } else if (isNumeric(command.substring(7))) {
                    taskNumber = Integer.parseInt(command.substring(7));
                    return true;
                } else {
                    throw new DukeException("The Delete command requires a number, separated by whitespace!");
                }
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
            return "No Deadline";
        }
    }

    private String returnTimeline(String command) {
        int startNumber = command.indexOf("/at");

        if (startNumber > 0 && command.length() > 3) {
            return command.substring(startNumber + 3);
        } else {
            return "No Timeline";
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

    /**
     * Marks tasks as done
     *
     * @throws DukeException Handles numbers less than 1
     * @return returns a String message
     */
    private String markDone() throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Mark Done:", 2);
        } else {
            if (taskNumber <= commands.size()) {
                Task taskToChange = commands.get(taskNumber - 1);
                taskToChange.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");

                System.out.println("   " + taskToChange);
                newLine();
                return "   " + taskToChange;
            } else {
                throw new DukeException("Task does not exist");
            }
        }

    }

    /**
     * Deletes Tasks from the list
     *
     * @throws DukeException Handles numbers less than 1
     * @return returns a String message
     */
    private String deleteTask() throws DukeException {
        if (taskNumber <= 0) {
            throw new DukeException("Delete Task:", 2);
        } else {
            if (taskNumber <= commands.size()) {
                System.out.println("Noted. I've removed this task: ");

                Task taskToDelete = commands.get(taskNumber - 1);
                commands.remove(taskNumber - 1);

                System.out.println("   " + taskToDelete);
                System.out.println("Now you have " + FeatureMain.commands.size() + " tasks in the list.");
                newLine();
                return "   " + taskToDelete;
            } else {
                throw new DukeException("Unable to Delete: Task does not exist");
            }
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
