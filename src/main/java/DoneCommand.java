import java.util.ArrayList;

public class DoneCommand extends Command {

    private ArrayList<Task> taskList;
    private String[] inputValues;

    public DoneCommand(ArrayList<Task> taskList, String[] inputValues) {
        this.taskList = taskList;
        this.inputValues = inputValues;
    }

    @Override
    public void execute() {
        try {
            int idx = Integer.parseInt(this.inputValues[1]);
            Task taskToComplete = this.taskList.get(idx - 1);
            if (taskToComplete.getIsDone()) {
                //if task is already completed
                System.out.println("     I have already marked this task as completed!");
            } else {
                taskToComplete.setIsDone(true);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + taskToComplete.toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("     OOPS!!! Please ensure a number is entered after done (eg: done 2)");
        } catch (IndexOutOfBoundsException e) {
            if (Integer.parseInt(this.inputValues[1]) <= 1) {
                System.out.println("     OOPS!!! You do not have any task in the list");
            } else {
                System.out.println("     OOPS!!! You do not have " + this.inputValues[1] + " tasks in the list");
            }
        }
    }
}
