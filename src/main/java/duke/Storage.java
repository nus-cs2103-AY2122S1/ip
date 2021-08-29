package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
/**
 * Loads tasks from hard disk and saves tasks to files after each command.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath to hard disk that stores the tasks.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            this.filePath = filePath;
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Loads in tasks from hard disk to a task list, when Duke starts up.
     *
     * @return ArrayList of tasks that is loaded in from hard disk.
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> result = new ArrayList<>(100);
        int counter = 0;

        try {
            Scanner dataScanner = new Scanner(new File(this.filePath));

            while (dataScanner.hasNext()) {
                convertToTask(dataScanner.nextLine(), result, counter);
                counter++;
            }
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

        return result;
    }

    /**
     * Adds Todo task to the hard disk.
     *
     * @param todo task to add.
     */
    public void addTodo(Todo todo) {
        try {
            String textToSave = "T/~/0/~/" + todo.toSavedFormat() + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Adds Deadline task to hard disk.
     *
     * @param deadline task to add.
     */
    public void addDeadline(Deadline deadline) {
        try {
            String textToSave = "D/~/0/~/" + deadline.toSavedFormat() + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();

        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Adds Event task to hard disk.
     *
     * @param event task to add.
     */
    public void addEvent(Event event) {
        try {
            String textToSave = "E/~/0/~/" + event.toSavedFormat() + "\n";

            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Marks a task in the hard disk as done.
     *
     * @param taskList list of tasks.
     * @param taskToMark position of task to mark as done in task list.
     */
    public void markAsDone(TaskList taskList, int taskToMark) {
        try {
            Task t = taskList.getTasks().get(taskToMark - 1);
            String taskType = t.getTaskType() == 0 ? "T" : (t.getTaskType() == 1 ? "D" : "E");
            String done = t.isDone() ? "1" : "0";
            String description = t.toSavedFormat();
            String textToSave = taskType + "/~/" + done + "/~/" + description;

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.set(taskToMark - 1, textToSave);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Deletes a task in the hard disk.
     *
     * @param taskToDelete position of task to delete from task list.
     */
    public void delete(int taskToDelete) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.remove(taskToDelete - 1);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    /**
     * Converts the task from format in hard disk into a new Task.
     *
     * Each task has 3 possible types: Todo, Deadline and Event.
     *
     * @param s a task from saved hard disk.
     * @param savedInputs list of tasks
     * @param counter
     */
    private static void convertToTask(String s, ArrayList<Task> savedInputs, int counter) {
        String[] savedTasks = s.split("/~/");

        String description = savedTasks[2];
        if (savedTasks.length == 3) {
            savedInputs.add(new Todo(description));
        } else if (savedTasks[0].equals("D")) {
            String by = savedTasks[3];
            LocalDate date = LocalDate.parse(by);
            savedInputs.add(new Deadline(description, date));
        } else {
            String at = savedTasks[3];
            LocalDate date = LocalDate.parse(at);
            savedInputs.add(new Event(description, date));
        }

        boolean isDone = savedTasks[1].equals("1");
        if (isDone) {
            savedInputs.get(counter).markAsDone();
        }
    }

}
