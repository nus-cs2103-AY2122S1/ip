package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EmptyTimeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Deadline;
import duke.tasks.Task;

/**
 * Represents command to create deadline
 */
public class DeadlineCommand extends Command {
    private int byIndex;
    private String time;
    private String description;

    /**
     * Creates deadline command
     * @param userInput userinput
     * @throws DukeException throws if user input is invalid
     */
    public DeadlineCommand(String userInput) throws DukeException {
        super(userInput);
        this.byIndex = this.getByIndex();
        this.time = this.getTime();
        this.description = this.getDescription();
    }

    /**
     * Execute deadline command
     * @param taskList The object that holds a list of Task
     * @param ui The object responsible for updating Ui response
     * @param storage The object responsible to save/load list of task to/from hard disk
     * @return String to be printed out to user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        //TaskList
        Task newTask = new Deadline(this.description, this.time);
        taskList.addTask(newTask);

        //Storage
        storage.save(taskList);

        //Ui
        String output = "";
        output += ui.showAddTask(newTask) + "\n";
        output += ui.showNumTask(taskList.getNumTask());
        return output;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    private String getTime() throws EmptyTimeException {
        String time = String.join(" ", userInputList.subList(this.byIndex + 1, userInputList.size()));

        if (time.equals("")) {
            throw new EmptyTimeException("deadline");
        } else {
            return time;
        }
    }

    private String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            return description;
        }
    }

    private int getByIndex() {
        return userInputList.indexOf("/by");
    }
}
