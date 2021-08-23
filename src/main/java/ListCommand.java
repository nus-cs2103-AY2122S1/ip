public class ListCommand implements Executable {
    public void execute(TaskList taskList) {
        if (taskList.tasks().size() == 0) {
            Ui.printString("No current task available.");
        } else {
            String out = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.tasks().size(); i++) {
                int index = i + 1;
                out = out + Ui.SPACE_STRING + index + "." + taskList.tasks().get(i) + "\n";
            }
            Ui.printList(out);
        }
    }
}