package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskType;
import duke.tasklist.TaskList;

public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    private final String restOfWords;

    private int id;

    private String updatedName;

    private String updatedDate;

    /**
     * Abstract constructor that will have to be implemented by all classes that extend Command.
     *
     * @param taskList that is passed by Duke.
     */
    public UpdateCommand(TaskList taskList, String rest) {
        super(taskList);
        this.restOfWords = rest;
    }

    public void setUpdatedName(String updatedName) {
        this.updatedName = updatedName;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public CommandResult execute() throws DukeException {
        parseThroughRest();
        Task task = this.getTaskList().get(this.id);
        String feedback = "I've updated this task in your list from\n";
        if (task.getTaskType().equals(TaskType.T)) {
            if (!checkIfUpdatedDateIsNull()) {
                throw new DukeException("This task is a Todo task, and can only accept a change in name.");
            }
            if (checkIfUpdatedNameIsNull()) {
                throw new DukeException("This task is a Todo task, and no updated name was supplied.");
            }
            Task updatedTask = task.updateName(this.updatedName);
            Task oldTask = this.getTaskList().updateTask(updatedTask, this.id);
            feedback = feedback + "\n" + oldTask.toString() + "\n to" + "\n" + updatedTask.toString();
            return new CommandResult(feedback, false);
        }
        if (checkIfUpdatedNameIsNull() && checkIfUpdatedDateIsNull()) {
            throw new DukeException("This is a Timed Task. You have to supply either an updated name OR a date.");
        }
        Task updatedTask = updateNameOfTask(task);
        Task updatedTask1 = updateDateOfTask(updatedTask);
        Task oldTask = this.getTaskList().updateTask(updatedTask1, this.id);
        feedback = feedback + "\n" + oldTask.toString() + "\n to" + "\n" + updatedTask1.toString();
        return new CommandResult(feedback, false);
    }

    private boolean checkIfUpdatedNameIsNull() {
        return this.updatedName == null;
    }

    private boolean checkIfUpdatedDateIsNull() {
        return this.updatedDate == null;
    }

    private Task updateNameOfTask(Task task) throws DukeException {
        if (checkIfUpdatedNameIsNull()) {
            return task;
        }
        return task.updateName(this.updatedName);
    }

    private Task updateDateOfTask(Task task) throws DukeException {
        if (checkIfUpdatedDateIsNull()) {
            return task;
        }
        return task.updateDateTime(this.updatedDate);
    }

    private void parseThroughRest() throws DukeException {
        String[] split = this.restOfWords.split("(?=/)");
        lookForAndSetId(split);
        lookForAndSetName(split);
        lookForAndSetDateTime(split);
    }

    private void lookForAndSetId(String[] splitString) throws DukeException {
        for (String s: splitString) {
            System.out.println(s);
            if (s.startsWith("/i")) {
                String res = s.substring(2);
                this.id = Integer.parseInt(res.trim());
                return;
            }
        }
        throw new DukeException(
                "Id was not passed when updating. Please try this format\n"
                        + "update /i (the id of the task to update) /n (the new name of the task) /d (the new date of the task)"
        );
    }

    private void lookForAndSetName(String[] splitString) {
        for (String s: splitString) {
            if (s.startsWith("/n")) {
                setUpdatedName(s.substring(2).trim());
            }
        }
    }

    private void lookForAndSetDateTime(String[] splitString) {
        for (String s: splitString) {
            if (s.startsWith("/d")) {
                setUpdatedDate(s.substring(2).trim());
            }
        }
    }
}
