import java.util.ArrayList;

public class DeleteCommand extends Command {

    private ArrayList<Task> taskList;
    private String[] inputValues;

    public DeleteCommand(ArrayList<Task> taskList, String[] inputValues) {
        this.taskList = taskList;
        this.inputValues = inputValues;
    }

    @Override
    public void execute() {
        try {
            int idx = Integer.parseInt(this.inputValues[1]);
            Task taskToDelete = this.taskList.remove(idx - 1);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + taskToDelete.toString());
            System.out.println("     Now you have " + this.taskList.size()
                    + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        } catch (NumberFormatException e) {
            System.out.println("     OOPS!!! Please ensure a number is entered after delete (eg: delete 2)");
        } catch (IndexOutOfBoundsException e) {
            if (Integer.parseInt(this.inputValues[1]) <= 1) {
                System.out.println("     OOPS!!! You do not have any task in the list");
            } else {
                System.out.println("     OOPS!!! You do not have " + this.inputValues[1] + " tasks in the list");
            }
        }
    }
}
