import java.time.DateTimeException;
import java.util.ArrayList;

public class EventCommand extends Command {

    private final ArrayList<Task> taskList;
    private final String[] inputValues;
    private final String command;

    public EventCommand(ArrayList<Task> taskList, String[] inputValues, String command) {
        this.taskList = taskList;
        this.inputValues = inputValues;
        this.command = command;
    }

    @Override
    public void execute() {
        if (this.inputValues.length == 1) {
            //first check for empty events
            System.out.println("     OOPS!!! The description and date/time of an event cannot be empty.");
        } else if (!this.command.contains("/at")) {
            //check for date separator
            System.out.println(
                    "     Invalid event format!\n" +
                    "     Please ensure you specify your date and time after a \"/at\"\n" +
                    "     Eg: event Attend physical lessons /at 2021-08-29 15:00");
        } else {
            int dateTimeIndex = this.command.indexOf("/");
            String description = this.command.substring(this.inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = this.command.substring(dateTimeIndex + 3).strip();

            try {
                Task event = new Event(description, dateTime);
                this.taskList.add(event);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + event.toString());
                System.out.println("     Now you have " + this.taskList.size()
                        + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
            } catch (DateTimeException exception) {
                System.out.println(
                        "     Error! Ensure your date and time is valid and formatted correctly!\n"
                                + "     Date: \"YYYY-MM-DD\" format, eg: 2021-08-23\n"
                                + "     Time: 24Hr format, \"HH:mm\", eg: 18:00");
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

}
