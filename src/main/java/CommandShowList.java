public class CommandShowList {
    @Override
    public void execute(TaskList taskList, UserInterface ui){
        for (Task task: taskList) {
            ui.print(task.toString());
        }
    }
}
