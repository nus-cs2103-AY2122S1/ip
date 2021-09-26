package banana;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;


/**
 * The Duke class is the program's
 * fundamental class.
 *
 * @author: Ravi Ananya
 */
public class Duke {

    private String filePath;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Duke class.
     * Initialises the storage and tasks
     * objects.
     *
     * @param filePath the file to write data to.
     */
    public Duke(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = storage.load(
                    new File(storage.getFilePath()));
            this.filePath = filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * You should have your own function to generate a
     * response to user input. Replace this stub with
     * your completed method.
     *
     * @param input the user input
     * @return the output
     */
    protected String getResponse(String input) {
        String output = "";
        try {
            Parser p = new Parser(input);
            output = p.parseInput(tasks);
            writeToFile();
        } catch (DukeException | IOException e) {
            output = e.getMessage();
        }
        return output;
    }

    /**
     * Writes to the Tasks.txt file everytime
     * a change in tasks has occurred.
     *
     * @throws IOException if not able to write to the file.
     */
    public void writeToFile() throws IOException {
        String text = "";
        FileWriter fw = new FileWriter(filePath, false);
        for (int i = 0; i < tasks.getSize(); i++) {
            String doneStr = "No";
            Task currentTask = tasks.getTask(i);
            assert currentTask != null;
            if (currentTask.getIsDone().equals("[X]")) {
                doneStr = "Yes";
            }
            if (currentTask instanceof ToDo) {
                text += "T ~ " + doneStr + " ~ "
                        + currentTask.getDescription() + "\n";
            } else if (currentTask instanceof Deadline) {
                Deadline dl = (Deadline) currentTask;
                text += "D ~ " + doneStr + " ~ "
                        + currentTask.getDescription() + " ~ "
                        + dl.getDeadLine() + "\n";
            } else if (currentTask instanceof Event) {
                Event ev = (Event) currentTask;
                text += "E ~ " + doneStr + " ~ "
                        + currentTask.getDescription() + " ~ "
                        + ev.getEvent() + "\n";
            } else {
                text += doneStr + " ~ "
                        + currentTask.getDescription() + "\n";
            }
        }
        fw.write(text);
        fw.close();
    }

}















