import java.time.DateTimeException;
import java.util.ArrayList;

public class DeadlineCommand extends Command {

    private final ArrayList<Task> taskList;
    private final String[] inputValues;
    private final String command;

    public DeadlineCommand(ArrayList<Task> taskList, String[] inputValues, String command) {
        this.taskList = taskList;
        this.inputValues = inputValues;
        this.command = command;
    }

    @Override
    public void execute() {
        if (this.inputValues.length == 1) {
            //first check if deadline is empty
            System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
        } else if (!this.command.contains("/by")) {
            //check for date separator
            System.out.println(
                    "     Invalid deadline format!\n"
                    + "     Please ensure you specify your date and time after a \"/by\"\n"
                    + "     Eg: deadline Submit Assignment /by 2021-08-29 15:00"
            );
        } else {
            int dateTimeIndex = this.command.indexOf("/");
            String description = this.command.substring(this.inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = this.command.substring(dateTimeIndex + 3).strip();
            try {
                Task deadline = new Deadline(description, dateTime);
                this.taskList.add(deadline);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + deadline.toString());
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
