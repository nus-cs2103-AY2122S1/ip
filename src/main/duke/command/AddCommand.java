package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

public class AddCommand extends Command {

    private Task taskToAdd;
    
    public AddCommand(String fullCommand) throws DukeException{
        parseTask(fullCommand);
    }

    private void parseTask(String fullCommand) throws DukeException{
        String type = fullCommand.split(" ")[0];
        switch (type) {
        case "todo":
            String todoDescription = fullCommand.replace("todo", "").trim();
            if (todoDescription.isEmpty()) {
                throw new DukeException("Empty Todo Command!");
            }
            this.taskToAdd = new TodoTask(todoDescription);
            break;
        case "deadline":
            String[] deadlineDescriptions = fullCommand.replace("deadline", "").split("/by");
            String deadlineDescription = deadlineDescriptions[0].trim();
            String deadlineTime = deadlineDescriptions[1].trim();
            if (deadlineDescription.isEmpty() || deadlineTime.isEmpty()) {
                throw new DukeException("Empty Deadline Command!");
            }
            this.taskToAdd = new DeadlineTask(deadlineDescription, deadlineTime);
            break;
        case "event":
            String[] eventDescriptions = fullCommand.replace("event", "").split("/at");
            String eventDescription = eventDescriptions[0].trim();
            String eventTime = eventDescriptions[1].trim();
            if (eventDescription.isEmpty() || eventTime.isEmpty()) {
                throw new DukeException("Empty Event Command!");
            }
            this.taskToAdd = new EventTask(eventDescription, eventTime);
            break;
        default:
            throw new DukeException("Invalid Command!");
        }
    }
  
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("Task Added!\n %s", taskToAdd));
        tasks.add(taskToAdd);
        storage.save(tasks);
    }

<<<<<<< Updated upstream
    public Boolean isExit() {
=======
    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
>>>>>>> Stashed changes
        return false;
    }

}
