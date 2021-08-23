import java.util.ArrayList;

public class EventCommand extends Command {

    private ArrayList<Task> taskList;
    private String[] inputValues;
    private String command;

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
            System.out.println("     Invalid event format! " +
                    "Please ensure you specify your date and time after a /at " +
                    "Eg: event Attend physical lessons /at NUS Monday 0800");
        } else {
            int dateTimeIndex = this.command.indexOf("/");
            String description = this.command.substring(this.inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = this.command.substring(dateTimeIndex + 3).strip();

            if (description.equals("") || dateTime.equals("")) {
                //catches the case if user types "event /at"
                System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
            } else {
                Task event = new Event(description, dateTime);
                this.taskList.add(event);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + event.toString());
                System.out.println("     Now you have " + this.taskList.size()
                        + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
            }
        }
    }

}
