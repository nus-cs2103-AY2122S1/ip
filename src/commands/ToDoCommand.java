package commands;

import components.Task;
import components.TaskList;
import components.ToDo;

public class ToDoCommand extends Command {
    AppState applicationState;
    String todo;

    public ToDoCommand(AppState applicationState, String todo) {
        this.applicationState = applicationState;
        this.todo = todo;
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(new ToDo(this.todo, false));
        System.out.println(String.format("Excellent! I've added this task: \n%s",
                taskList.showTask(taskList.size() - 1)));
        return new AppState(applicationState.userExit, newTaskList);
    }



}
