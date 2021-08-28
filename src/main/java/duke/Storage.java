package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks as loaded from the file given by the file path.
     *
     * @return A list of the loaded tasks.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public List<Task> load() throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner reader = new Scanner(file);
        List<Task> taskList = new ArrayList<>();

        while (reader.hasNextLine()) {
            String taskString = reader.nextLine();
            String[] splitDescription = taskString.split(" \\| ");
            Task task;

            if (splitDescription[0].equals("T")) {
                task = new Todo(splitDescription[2]);
            } else if (splitDescription[0].equals("D")) {
                task = new Deadline(splitDescription[2], splitDescription[3]);
            } else {
                task = new Event(splitDescription[2], splitDescription[3]);
            }

            if (splitDescription[1].equals("1")) {
                task.markAsDone();
            }
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Updates the saved file with the latest tasks.
     *
     * @param taskList The most updated tasks.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public void update(List<Task> taskList) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(this.filePath);
        for (Task task : taskList) {
            if (task instanceof Todo) {
                out.printf("T | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription());
            } else if (task instanceof Deadline) {
                out.printf("D | %s | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription(), (
                        (Deadline) task).getDeadline());
            } else {
                out.printf("E | %s | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription(), (
                        (Event) task).getTime());
            }
        }
        out.close();
    }
}
