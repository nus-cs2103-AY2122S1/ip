package commands;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import tasks.Event;
import tasks.TaskList;
import utils.Util;

public class EventCommand extends Command {
    private int byIndex;

    public EventCommand(String userInput) {
        super(userInput);
        this.byIndex = this.getAtIndex();
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        try {
            String description = this.getDescription();
            String time = this.getTime();

            //Update tasks.TaskList
            Event event = new Event(description, time);
            oldTaskList.addTask(event);
            this.newTaskList = oldTaskList;

            //Update Log
            this.newLog += Util.taskAddConfirmation(event, this.newTaskList.getNumTask());

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
            throw new EmptyTimeException("event");
        } else {
            return time;
        }
    }

    public String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("event");
        } else {
            return description;
        }
    }

    public int getAtIndex() {
        return userInputList.indexOf("/at");
    }
}
