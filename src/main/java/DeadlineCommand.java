import java.util.ArrayList;

public class DeadlineCommand extends Command {

    private ArrayList<Task> taskList;
    private String[] inputValues;
    private String command;

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
            System.out.println("     Invalid deadline format! " +
                    "Please ensure you specify your date and time after a /by " +
                    "Eg: deadline Submit Assignment /by Thursday 2359");
        } else {
            int dateTimeIndex = this.command.indexOf("/");
            String description = this.command.substring(this.inputValues[0].length() + 1, dateTimeIndex).strip();
            String dateTime = this.command.substring(dateTimeIndex + 3).strip();

            if (description.equals("") || dateTime.equals("")) {
                //catches the case if users just type "deadline /by"
                System.out.println("     OOPS!!! The description and date/time of a deadline cannot be empty.");
            } else {
                Task deadline = new Deadline(description, dateTime);
                this.taskList.add(deadline);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + deadline.toString());
                System.out.println("     Now you have " + this.taskList.size()
                        + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
            }
        }

    }
}
