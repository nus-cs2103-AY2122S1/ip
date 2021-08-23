import java.util.ArrayList;

public class TodoCommand extends Command {

    private final ArrayList<Task> taskList;
    private final String[] inputValues;
    private final String command;

    public TodoCommand(ArrayList<Task> taskList, String[] inputValues, String command) {
        this.taskList = taskList;
        this.inputValues = inputValues;
        this.command = command;
    }

    @Override
    public void execute() {
        if (this.inputValues.length == 1) {
            //catch empty to-do
            System.out.println("     OOPS!!! The description of a todo cannot be empty.");
        } else {
            String description = command.substring(this.inputValues[0].length() + 1);
            Task toDo = new ToDo(description);
            this.taskList.add(toDo);
            System.out.println("     Got it. I have added this task:");
            System.out.println("       " + toDo.toString());
            System.out.println("     Now you have " + this.taskList.size()
                    + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
        }
    }
}
