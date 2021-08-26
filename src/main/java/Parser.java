import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String getCommand(String input) throws DukeException {
        if (input.trim().equals("bye")){
            return "bye";
        } else if (input.trim().equals("list")) {
            return "list";
        } else if (input.matches("done (.*)")) {
            return "done";
        } else if (input.matches("delete (.*)")) {
            return "delete";
        } else if (input.matches("todo(.*)")){
            return "todo";
        } else if (input.matches("deadline(.*)")) {
            return "deadline";
        } else if (input.matches("event(.*)")) {
            return "event";
        } else {
            throw new DukeException("I'm sorry, but I don't understand what that means :(");
        }
    }

    public static int getTaskNo(String input, String command) throws DukeException {
        try {
            String text = input.substring(command.length() + 1);
            int taskNo = Integer.parseInt(text);
            return taskNo;
        } catch (NumberFormatException e) {
            String str = String.format("Please use the format %s <task No.>", command);
            throw new DukeException(str);
        } catch (StringIndexOutOfBoundsException e) {
            String str = String.format("Please use the format %s <task No.>", command);
            throw new DukeException(str);
        }
    }

    public static String[] getEventFields(String input) throws DukeException {
        String[] fields = new String[2];
        try {
            String taskDesc = input.substring(6);
            fields = taskDesc.split(" /at ", 2);
            //make sure not empty
            String time = fields[1];
            return fields;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The Event description and time cannot be empty. " +
                    "Please use format event <description> /at <time>.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use format event <description> /at <time>.");
        }
    }

    public static String[] getDeadlineFields(String input) throws DukeException {
        String[] fields = new String[2];
        try {
            String taskDesc = input.substring(9);
            fields = taskDesc.split(" /by ", 2);
            LocalDate date = LocalDate.parse(fields[1]);
            return fields;
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use format deadline <description> /by <yyyy-mm-dd>.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The Deadline description cannot be empty. " +
                    "Please use format deadline <description> /by <time>.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use format deadline <description> /by <time>.");
        }

    }

    public static String[] getToDoFields(String input) throws DukeException {
        String[] fields = new String[1];
        try {
            String taskDesc = input.trim().substring(5);
            fields = new String[]{taskDesc};
            return fields;
        } catch(StringIndexOutOfBoundsException e) {
            throw new DukeException("The todo task description cannot be empty. " +
                    "Please use format todo <desc>");
        }
    }

}
