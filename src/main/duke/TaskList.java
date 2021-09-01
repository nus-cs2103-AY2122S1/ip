package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task>{
    protected void load(List<String> list) {
        for(String s : list) {
            try {
                super.add(Parser.fileContentsToTask(s));
            } catch (DukeException e) {
                Ui.printErrorMessage(e);
            }
        }
    }
}
