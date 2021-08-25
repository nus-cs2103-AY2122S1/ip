import java.util.Objects;
import java.util.Scanner;

/**
 * This class takes in a saved file and loads whatever tasks were saved in it.
 */

public class FileLoader {
    Scanner saveFile;

    public FileLoader(Scanner saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Saved file should be structured in this manner:
     * - In each line, start with a T(odo), D(eadline), E(vent)
     * - Space out each detail with a "|'
     * - If empty line, assume end of file
     *
     */
    public TaskList load() {
        TaskList lst = new TaskList();
        while (saveFile.hasNextLine()) {
            String fullLine = saveFile.nextLine();
            if (Objects.equals(fullLine, "")) {
                break;
            }
            String[] data = fullLine.split("\\|", 4);
            String task = data[0].substring(0, 1);
            String state = data[1].substring(1, 2);
            String taskName = data[2].substring(1);
            switch (task) {
            case "T":
                ToDo eventTodo = new ToDo(taskName);
                if (state.indexOf("1") >= 0) {
                    eventTodo.doneTask(false);
                }
                lst.add(eventTodo, false);
                break;
            case "D":
                String deadline = data[3];
                Deadline eventDeadline = new Deadline(taskName, deadline);
                if (state.indexOf("1") >= 0) {
                    eventDeadline.doneTask(false);
                }
                lst.add(eventDeadline, false);
                break;
            case "E":
                String time = data[3];
                Event eventEvent = new Event(taskName, time);
                if (state.indexOf("1") >= 0) {
                    eventEvent.doneTask(false);
                }
                lst.add(eventEvent, false);
                break;
            }
        }
        return lst;
    }
}
