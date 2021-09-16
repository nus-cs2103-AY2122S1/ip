package katheryne.command;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.task.Todo;

public class FindCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "FIND";
    private final String keyword;

    /**
     * Constructs the EventCommand by putting the processedRemainingText into appropriate variables.
     *
     * @param processedRemainingText The first item is the description; the second is the time the event is
     * @throws KatheryneException If the date is in the wrong format
     */
    FindCommand(String[] processedRemainingText) {
        this.keyword = processedRemainingText[0];
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        TaskList subList = taskList.tasksContaining(keyword);
        if (subList.isEmpty()) {
            ui.say("I couldn't find any tasks containing '" + keyword + "'. Try another word?");
        } else {
            ui.say("Tasks containing '" + keyword + "' are listed below. " 
                    + "The number of tasks is " + subList.getSize() + ".");
            ui.listTasks(subList);
        }
        
    }

}
