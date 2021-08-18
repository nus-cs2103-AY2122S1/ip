package commands;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import tasks.Deadline;
import tasks.TaskList;
import utils.Util;

public class DeadlineCommand extends Command {
    private int byIndex;

    public DeadlineCommand(String userInput) {
        super(userInput);
        this.byIndex = this.getByIndex();
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        try {
            String description = this.getDescription();
            String time = this.getTime();

            //Update tasks.TaskList
            Deadline deadline = new Deadline(description, time);
            oldTaskList.addTask(deadline);
            this.newTaskList = oldTaskList;

            //Update Log
            this.newLog += Util.taskAddConfirmation(deadline, this.newTaskList.getNumTask());

        } catch (DukeException e) {
            //Unchanged tasks.TaskList
            this.newTaskList = oldTaskList;
            //Log out error message
            this.newLog = e.getMessage();
        }
    }

    public String getTime() throws EmptyTimeException {
        String time = String.join(" ", userInputList.subList(this.byIndex + 1, userInputList.size()));

        if (time.equals("")) {
            throw new EmptyTimeException("deadline");
        } else {
            return time;
        }
    }

    public String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            return description;
        }
    }

    public int getByIndex() {
        return userInputList.indexOf("/by");
    }
}
