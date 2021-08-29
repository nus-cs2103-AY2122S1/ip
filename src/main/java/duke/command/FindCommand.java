package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;
import duke.data.task.Event;

public class FindCommand extends Command {
    private String input;

    public FindCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
        if (input.length() == 4) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("find"));
        }
        String wordToFind = input.substring(5);
        taskList.findTask(wordToFind);
    }
}