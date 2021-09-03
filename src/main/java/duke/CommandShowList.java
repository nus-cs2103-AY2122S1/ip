package duke;

public class CommandShowList extends Command {

    public CommandShowList () {
        super();
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui){
        for (Task task: taskList.getTasks()) {
            ui.print(task.toString());
        }
    }
}
