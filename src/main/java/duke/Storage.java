package duke;

import static duke.Deadline.PRINTDATEFORMAT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage space that loads the <code>list.txt</code> of existing tasks and saves new
 * changes to the <code>list.txt</code>.
 */
public class Storage {

    private final String filePath;
    private final Duke duke;

    /**
     * Returns a Storage object.
     *
     * @param filePath String of the filepath of the list from source folder.
     * @param duke the Duke object that is the parent.
     */
    public Storage(String filePath, Duke duke) {
        this.filePath = filePath;
        this.duke = duke;
    }

    /**
     * Loads the existing <code>Task</code>s from the <code>TaskList</code> when Duke is ran.
     *
     * @throws FileNotFoundException if <code>filePath</code> specified is invalid.
     */
    protected String loadFileToList() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] taskData = currentLine.split("\\|");
            assert taskData.length >= 3 : "there should be a category, binary and description minimally";

            String category = taskData[0].trim();
            assert category.matches("T|D|E") : "only T, D or E valid";

            String isDoneChar = taskData[1].trim();
            assert isDoneChar.matches("1|0") : "only 0 or 1 valid";
            boolean isDone = isDoneChar.equals("1");
            String description = taskData[2].trim();
            String tags = taskData[3].trim();

            if (category.equals("T")) {
                duke.getTasks().createTask(description, "", Task.Category.TODO, isDone, false, tags);
                continue;
            }

            assert taskData.length == 5 : "there should be category, binary, description, tags and date";
            String time = taskData[4].trim();

            if (category.equals("D")) {
                duke.getTasks().createTask(description, time, Task.Category.DEADLINE, isDone, false, tags);
                continue;
            }
            if (category.equals("E")) {
                duke.getTasks().createTask(description, time, Task.Category.EVENT, isDone, false, tags);
            }
        }

        String showListMessage = duke.getUi().showListUponLoad();
        return showListMessage;
    }

    /**
     * Saves the new <code>Task</code>s from the <code>TaskList</code>
     * after changes are made to the <code>TaskList</code>.
     *
     * @throws IOException if <code>filePath</code> specified is invalid.
     */
    protected String saveListToFile() throws IOException {

        FileWriter fw = new FileWriter(filePath);
        String newInput = "";

        for (Task task : this.duke.getTasks().getList()) {

            int done = task.isDone ? 1 : 0;
            String description = task.description;
            ArrayList<String> tags = task.tags;

            switch (task.category) {
            case TODO:
                newInput = newInput + ("T | " + done + " | " + description + " | " + tags + "\n");
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                Object storeBy = deadline.by == null ? deadline.byDate.format(PRINTDATEFORMAT) : deadline.by;
                newInput = newInput + ("D | " + done + " | " + description + " | " + tags + " | " + storeBy + "\n");
                break;
            case EVENT:
                Event event = (Event) task;
                Object storeAt = event.at == null ? event.atDate.format(PRINTDATEFORMAT) : event.at;
                newInput = newInput + ("E | " + done + " | " + description + " | " + tags + " | " + storeAt + "\n");
                break;
            default:
            }
        }

        fw.write(newInput);
        fw.close();

        String saveListMessage = duke.getUi().saveList();
        return saveListMessage;
    }
}
