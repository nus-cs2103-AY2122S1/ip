package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class takes in a saved file and loads whatever tasks were saved in it.
 */
public class Storage {
    String filePath;
    Scanner saveFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Storage taking in a Scanner file
     *
     * @param saveFile scanner file
     */
    public Storage(Scanner saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Constructor for Storage taking in a filePath
     *
     * @param filePath filePath to the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File directory = new File(filePath);
        try {
            File dataFile = directory.getParentFile();
            if (dataFile.mkdir()) {
                Ui.createNewDirectory();
            }
            if (directory.createNewFile()) {
                Ui.createNewFile();
            }
            File file = new File(filePath);
            this.saveFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundError();
        } catch (IOException e) {
            System.out.println("Input error!");
        }
    }

    /**
     * Saved file should be structured in this manner:
     * - In each line, start with a T(odo), D(eadline), E(vent)
     * - Space out each detail with a "|'
     * - If empty line, assume end of file
     *
     * @return a TaskList with all the saved details.
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
                if (state.contains("1")) {
                    eventTodo.doneTask(false);
                }
                lst.add(eventTodo, false);
                break;
            case "D":
                String deadline = data[3].substring(1);
                Deadline eventDeadline = new Deadline(taskName, LocalDateTime.parse(deadline, formatter));
                if (state.contains("1")) {
                    eventDeadline.doneTask(false);
                }
                lst.add(eventDeadline, false);
                break;
            case "E":
                String time = data[3].substring(1);
                Event eventEvent = new Event(taskName, LocalDateTime.parse(time, formatter));
                if (state.contains("1")) {
                    eventEvent.doneTask(false);
                }
                lst.add(eventEvent, false);
                break;
            }
        }
        return lst;
    }

    /**
     * Saves the current storage file based on the input given.
     *
     * @param input input given, typically the full string of whatever tasks are on the task list.
     */
    public void save(String input) {
        try {
            PrintWriter newFile = new PrintWriter(this.filePath);
            newFile.println(input);
            newFile.close();
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundError();
        }
    }
}
