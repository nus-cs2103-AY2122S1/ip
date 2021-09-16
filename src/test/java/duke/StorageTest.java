package duke;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void storageTest() throws DukeException {
        String string = "D | 1 | qerewlrqwer | 2022-12-12 | 23:59";

        TaskList taskList = new TaskList();
        List<String> lines = new ArrayList<String>();
        lines.add(string);

        for (String str : lines) {
            String[] strparse = str.split(Pattern.quote(" | "));
            // incorrect task listed for some reason
            if (strparse.length < 3 || strparse.length > 5) {
                continue;
            } else if (strparse[0].equals("T")) {
                taskList.addReadTodo(strparse[2], Integer.parseInt(strparse[1]));
            } else if (strparse[0].equals("D")) {
                if (strparse.length < 5) {
                    taskList.addReadDeadline(strparse[2],
                            Integer.parseInt(strparse[1]), strparse[3], null);
                } else {
                    taskList.addReadDeadline(strparse[2],
                            Integer.parseInt(strparse[1]), strparse[3], strparse[4]);
                }
            } else if (strparse[0].equals("E")) {
                if (strparse.length < 5) {
                    //event has a time
                    taskList.addReadEvent(strparse[2],
                            Integer.parseInt(strparse[1]), strparse[3], null);
                } else {
                    //event doesn't have a time
                    taskList.addReadEvent(strparse[2],
                            Integer.parseInt(strparse[1]), strparse[3], strparse[4]);
                }
            }
        }
        assertEquals("[D] [X] qerewlrqwer (by: Dec 12 2022 11.59pm)",
                taskList.getTask(1).toString());
    }
}
