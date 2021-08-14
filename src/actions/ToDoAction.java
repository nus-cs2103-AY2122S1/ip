package actions;

import components.TaskList;
import components.ToDo;

public class ToDoAction extends Action {
    AppState applicationState;
    String todo;

    public ToDoAction(AppState applicationState, String todo) {
        this.applicationState = applicationState;
        this.todo = todo;
    }

    public AppState run() {
        TaskList taskList = this.applicationState.taskList;
        TaskList newTaskList = taskList.addTask(ToDo.of(this.todo, false));
        System.out.println(String.format("Excellent! I've added this task:\n%s",
                taskList.showTask(taskList.size() - 1)));
        return new AppState(applicationState.userExit, newTaskList);
    }



}
