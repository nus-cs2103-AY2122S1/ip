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

import duke.errors.ArchiveException;
import duke.errors.DukeException;
import duke.errors.FileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
/**
 * Loads tasks from hard disk and saves tasks to files after each command.
 */
public class Storage {
    private String filePath;
    private Ui ui;

    /**
     * Constructor.
     *
     * @param filePath to hard disk that stores the tasks.
     * @throws FileException
     */
    public Storage(String filePath, Ui ui) throws FileException {
        this.ui = ui;

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();

            this.filePath = filePath;
        } catch (IOException ioException) {
            throw new FileException("create new directory or file.");
        }
    }

    /**
     * Loads in tasks from hard disk to a task list, when Duke starts up.
     *
     * @return ArrayList of tasks that is loaded in from hard disk.
     * @throws FileException
     */
    public ArrayList<Task> load() throws FileException {
        ArrayList<Task> result = new ArrayList<>(100);
        int counter = 0;

        try {
            Scanner dataScanner = new Scanner(new File(this.filePath));

            while (dataScanner.hasNext()) {
                convertToTask(dataScanner.nextLine(), result, counter);
                counter++;
            }
        } catch (IOException ioException) {
            throw new FileException("load data from hard disk.");
        }

        return result;
    }

    /**
     * Adds a task to hard disk.
     *
     * @param task task to add.
     */
    public void add(Task task) throws FileException {
        String textToSave = task.getTaskType() + "/~/0/~/" + task.toSavedFormat() + "\n";

        try {
            FileWriter fw = new FileWriter("src/data/duke.txt", true);
            fw.write(textToSave);
            fw.close();
        } catch (IOException ioException) {
            throw new FileException("add Task to hard disk.");
        }
    }

    /**
     * Marks a task in the hard disk as done.
     *
     * @param taskList list of tasks.
     * @param taskToMark position of task to mark as done in task list.
     */
    public void markAsDone(TaskList taskList, int taskToMark) throws FileException {
        try {
            Task t = taskList.getTasks().get(taskToMark - 1);
            assert t != null : "task to get from taskList is null";
            char taskType = t.getTaskType();
            String done = t.isDone() ? "1" : "0";
            String description = t.toSavedFormat();
            String textToSave = taskType + "/~/" + done + "/~/" + description;

            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.set(taskToMark - 1, textToSave);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            throw new FileException("mark task as done in hard disk.");
        }
    }

    /**
     * Deletes a task in the hard disk.
     *
     * @param taskToDelete position of task to delete from task list.
     */
    public void delete(int taskToDelete) throws FileException {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Path
                    .of("src/data/duke.txt")));
            fileContent.remove(taskToDelete - 1);

            Files.write(Path.of("src/data/duke.txt"), fileContent);
        } catch (IOException ioException) {
            throw new FileException("delete task from hard disk.");
        }
    }

    /**
     * Clears the hard disk.
     */
    public void clear() throws FileException {
        File file = new File(filePath);
        try {
            if (file.delete()) {
                file.createNewFile();
            }
        } catch (IOException ioException) {
            throw new FileException("clear the hard disk.");
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

        assert (savedTasks.length == 3 || savedTasks.length == 4) : "Improper formatting of ";
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

        assert (savedTasks[1].equals("0") || savedTasks[1].equals("1"))
                : "improper saved format for task's done status";
        boolean isDone = savedTasks[1].equals("1");
        if (isDone) {
            savedInputs.get(counter).markAsDone();
        }
    }

    /**
     * Loads in an Archive file into the hard disk and tasklist.
     *
     * @param archiveName of archive file to load from.
     * @param taskList to load into.
     * @throws DukeException if task list or hard disk are not empty.
     */
    public void loadArchive(String archiveName, TaskList taskList) throws ArchiveException {
        if (!taskList.getTasks().isEmpty()) {
            throw new ArchiveException("as task list is not empty, cannot load Archive.");
        }

        File file = new File(this.filePath);
        if (file.length() != 0) {
            throw new ArchiveException("as hard disk is not empty, cannot load Archive.");
        }

        String archivePath = "src/archive/" + archiveName + ".txt";
        File archiveFile = new File(archivePath);
        if (!archiveFile.exists()) {
            throw new ArchiveException("that does not exist.");
        }

        try {
            file.delete();
            Files.copy(archiveFile.toPath(), file.toPath());

            taskList.getTasks().clear();
            ArrayList<Task> temp = new ArrayList<>();
            int counter = 0;
            Scanner archiveScanner = new Scanner(new File(archivePath));
            while (archiveScanner.hasNext()) {
                convertToTask(archiveScanner.nextLine(), temp, counter);
                counter++;
            }

            for (Task t : temp) {
                taskList.addTask(t);
            }
            archiveScanner.close();

        } catch (IOException ioException) {
            throw new ArchiveException("to save a new Archive.");
        }

    }

}
