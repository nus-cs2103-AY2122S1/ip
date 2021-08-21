import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Executes commands from user input for record keeping of tasks.
 * Exits the programme when "bye" is entered.
 */
public class Duke {

    /**
     * Returns true if a valid done operation is entered.
     * False otherwise.
     * 
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDoneOps(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 6) {
            return false;
        }
        if (input.startsWith("done ")) {
            try {
                Integer.parseInt(input.substring(5));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns true if a valid delete operation is entered.
     * False otherwise.
     * 
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDeleteOps(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 8) {
            return false;
        }
        if (input.startsWith("delete ")) {
            try {
                Integer.parseInt(input.substring(7));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean isListOps(String input) throws DukeException {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length < 6) {
            return false;
        }
        if (input.startsWith("list ")) {
            try {
                LocalDate holder = LocalDate.parse(input.substring(5).trim());
                return true;
            } catch (DateTimeParseException e) {
                throw new DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
            }
        }
        return false;
    }
    
    public enum Command {
        TODO("todo"), DEADLINE("deadline"), EVENT("event"), 
        DONE("done"), DELETE("delete"), BYE("bye"), LIST("list");
        private String value;
        private Command(String value) {
            this.value = value;
        }
    }

    /**
     * Executes the main programme where Duke is activated to receive user input and perform corresponding operations.
     * Exits when "bye" is entered.
     * 
     * @param args A String array from user input
     * @throws DukeException Exception that is specific to Duke where invalid input is detected
     */
    public static void main(String[] args) throws DukeException, IOException {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jackie here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        // Bye to check if bye is entered by the user and therefore to determine to terminate the programme.
        boolean bye = false;
        // Executed to check if there is a valid operation that has been done in a cycle so to determine if to throw.
        // DukeException.
        Scanner myObj = new Scanner(System.in);
        TaskList taskList = new TaskList();


        while (!bye) {
            try {
                boolean ifExecuted = false;
                // Receives user input and obtains first word.
                String temp = myObj.nextLine();
                String commandHolder = temp.split(" ", 2)[0];
                
                for (Command c : Command.values()) {
                    
                    // Goes through the Command Enum to check any matches with valid commands
                    if (c.value.equals(commandHolder)) {

                        switch (c) {

                        case BYE:
                            System.out.println("____________________________________________________________\n"
                                    + "Bye bye. Love you\n"
                                    + "____________________________________________________________\n");
                            bye = true;
                            ifExecuted = true;
                            break;

                        case LIST:
                            if (taskList.size() == 0) {
                                System.out.println("____________________________________________________________\n"
                                        + "Darling, you have nothing in your list though \n" 
                                        +"____________________________________________________________\n");
                            } else if (isListOps(temp)) {
                                taskList.listSchedule(temp);
                            } else {
                                System.out.println(taskList.toString());
                            }
                            ifExecuted = true;
                            break;

                        case DONE:
                            if (isDoneOps(temp)) {
                                int index = Integer.parseInt(temp.substring(5));
                                if (index > taskList.size()) {
                                    throw new DukeException("☹ oopsie!!! The specified task does not exit.");
                                }
                                taskList.doneTask(index);
                                ifExecuted = true;

                            } else {
                                throw new DukeException("☹ Would you specify the task for me my dear?");
                            }
                            break;

                        case DELETE:
                            if (isDeleteOps(temp)) {
                                int index = Integer.parseInt(temp.substring(7));
                                if (index > taskList.size()) {
                                    throw new DukeException("☹ oopsie!!! The specified task does not exit.");
                                }
                                taskList.deleteTask(index);
                                ifExecuted = true;
                            } else {
                                throw new DukeException("☹ Would you specify the task for me my dear?");
                            }
                            break;

                        case TODO:
                            if (temp.trim().equals("todo")) {
                                throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                            } else {
                                taskList.addTodo(temp);
                                ifExecuted = true;
                            }
                            break;

                        case DEADLINE:
                            if (temp.trim().equals("deadline")) {
                                throw new DukeException(
                                        " ☹ OOPS!!! The description of a deadline cannot be empty.");
                            } else if (temp.split("/", 2).length == 1) {
                                throw new DukeException(
                                        " ☹ OH MY DEAR!!! " +
                                                "Please enter a time after / following the deadline description");
                            } else {
                                taskList.addDeadline(temp);
                                ifExecuted = true;
                            }
                            break;

                        case EVENT:
                            if (temp.trim().equals("event")) {
                                throw new DukeException(" ☹ OOPS!!! The description of a event cannot be empty.");
                            } else if (temp.split("/", 2).length == 1) {
                                throw new DukeException(
                                        " ☹ OH MY DEAR!!! " +
                                                "Please enter a date after / following the event description");
                            } else {
                                taskList.addEvent(temp);
                                ifExecuted = true;
                            }
                            break;
                                
                        }
                    } 
                }
                if (!ifExecuted) {
                    throw new DukeException("☹ oopsie!!! I'm sorry my dear but I can't do that for you");
                }
                
            } catch (DukeException e) {
                System.out.println("____________________________________________________________\n"
                        + e.getMessage()
                        + "\n"
                        + "____________________________________________________________\n");
            }
        }
    }
}
