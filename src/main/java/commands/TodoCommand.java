package commands;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import tasks.TaskList;
import tasks.Todo;
import utils.Util;

public class TodoCommand extends Command {
    public TodoCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        try {
            String description = this.getDescription();

            //Update tasks.TaskList
            Todo todo = new tasks.Todo(description);
            oldTaskList.addTask(todo);
            this.newTaskList = oldTaskList;

            //Update Log
            this.newLog += Util.taskAddConfirmation(todo, this.newTaskList.getNumTask());

        } catch (DukeException e) {
            //Unchanged tasks.TaskList
            this.newTaskList = oldTaskList;
            //Log out error message
            this.newLog = e.getMessage();
        }
    }

    public String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ",
                userInputList.subList(1, userInputList.size()));
        if (description.equals("")) {
            throw new EmptyDescriptionException("todo");
        } else {
            return description;
        }
    }
}
