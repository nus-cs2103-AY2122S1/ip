package commands;

import components.TaskList;

public class MarkDoneCommand extends Command {
    AppState applicationState;
    int index;

    public MarkDoneCommand(AppState applicationState, int index) {
        this.applicationState = applicationState;
        this.index = index;
        TaskList taskList = this.applicationState.taskList;
        if (!(1 <= index && index <= taskList.size())) {
            System.out.println("Index is out of bounds.");
        }
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.markTaskDone(index);
        System.out.println(String.format("Nice! I've marked this task as done: \n%s",
                newTaskList.showTask(index)));
        return new AppState(applicationState.userExit, newTaskList);
    }
}
