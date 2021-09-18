package winston;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

/**
 * Represents an update command from the parent abstract class Command.
 */
public class UpdateCommand extends Command{
    private int index;
    private final String[] arr;
    private final String userInput;

    /**
     * Constructor for UpdateCommand.
     *
     * @param taskList the taskList with the list of tasks desired.
     * @param arrOfCommand The array of user instructions that is split by spaces and lowerCased.
     * @param userInput Instructions from the user.
     */
    public UpdateCommand(TaskList taskList, String[] arrOfCommand, String userInput) {
        super(taskList);
        arr = arrOfCommand;
        this.userInput = userInput;
    }

    /**
     * Tests if  user update command is valid.
     * 
     * @return a boolean on whether the user instructions are valid.
     */
    private boolean isValid() {
        if (arr.length < 4) {
            return false;   
        }
        index = Integer.parseInt(arr[1]);
        String cmd = arr[2];
        return cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event");
    }

    /**
     * Returns a task based on what the user wants to change the current task to.
     * 
     * @param str A string of the same format as parse from class Parser.
     * @return A child of parent class task.
     */
    public Task updateTask(String str) {
        String cmd = arr[2];
        if (isValid()) {
            if (cmd.equals("todo")) {
                try {
                    return new ToDoTask(str.substring(5));
                } catch (InputMismatchException | IndexOutOfBoundsException e) {
                    return null;
                }
            } else if (cmd.equals("deadline")) {
                try {
                    int endIndex = str.indexOf("/by ");
                    String date = str.substring(endIndex + 4);
                    String task = str.substring(str.indexOf(" ") + 1, endIndex);
                    LocalDate.parse(date);
                    return new DeadLine(task, date);
                } catch (InputMismatchException | IndexOutOfBoundsException | DateTimeParseException e) {
                    return null;
                }
            } else if (cmd.equals("event")) {
                try {
                    int endIndex = str.indexOf("/at ");
                    String date = str.substring(endIndex + 4);
                    String task = str.substring(str.indexOf(" ") + 1, endIndex);
                    LocalDate.parse(date);
                    return new Event(task, date);
                } catch (InputMismatchException | IndexOutOfBoundsException | DateTimeParseException e) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * Prints a message to let user's know that the command given is either invalid or
     * the update command has worked.
     * 
     * @return A string of what has happened.
     */
    @Override
    public String run() {
        Task task = null;
        try{
            String updateInstructions = userInput.substring(9);
            task = updateTask(updateInstructions);
        } catch (IndexOutOfBoundsException e) {
            return Ui.invalidTask("Invalid Task");
        }
        if (task == null) {
            return Ui.invalidTask("Invalid Task");
        } else {
            taskList.setTask(task, index - 1);
            String result = Ui.printList(taskList) + Ui.updateMessage(index);
            assert (!result.equals(""));
            return result;
        }
    }
}
