package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {
    /** The filePath to load and save the tasks. **/
    private final String filePath;

    /** The file to load and save the tasks. **/
    private final File file;

    /**
     * A public constructor to initialized the Storage
     *
     * @param filePath The given filePath.
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        this.file = createFile(filePath);
    }

    /**
     * A private method to open or create a file for storage.
     *
     * @throws DukeException Throw DukeException if cannot open and create such file.
     */
    private File createFile(String filePath) throws DukeException {
        try {
            File f = new File(filePath);
            if (!f.getParentFile().exists()) {
                boolean hasDir = f.getParentFile().mkdir();
                assert hasDir : "hasDir should be true";
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            return f;
        } catch (IOException e) {
            throw new DukeException("Cannot find or create such file.");
        }
    }

    /**
     * A method to load the task from the file, and add them to the data structure
     * in TaskList
     *
     * @return An ArrayList of Task, the list of tasks loaded from the file.
     * @throws DukeException Exception thrown when taskList cannot be loaded.
     */
    public ArrayList<Task> loadTaskList() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            int n = sc.nextInt();
            ArrayList<Task> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int type = sc.nextInt();
                int isDone = sc.nextInt();
                sc.nextLine();
                String taskName = sc.nextLine();
                String taskDate = sc.nextLine();
                String taskTime = sc.nextLine();

                assert (type == 0 || type == 1 || type == 2) : "Variable type should be 0 or 1 or 2";

                Task newTask = null;
                if (type == 1) {
                    newTask = new Todo(taskName);
                } else if (type == 2) {
                    LocalDate date = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalTime time = null;
                    if (!taskTime.equals("null")) {
                        time = LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
                    }
                    newTask = new Deadline(taskName, date, time);
                } else if (type == 3) {
                    LocalDate date = LocalDate.parse(taskDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalTime time = null;
                    if (!taskTime.equals("null")) {
                        time = LocalTime.parse(taskTime, DateTimeFormatter.ofPattern("HHmm"));
                    }
                    newTask = new Event(taskName, date, time);
                }

                if (isDone == 1) {
                    assert (newTask instanceof Task) : "newTask should be initialized";
                    newTask.markAsDone();
                }

                result.add(newTask);
            }
            return result;
        } catch (IOException e) {
            throw new DukeException("Cannot load task list from file: " + filePath);
        } catch (NoSuchElementException e) {
            throw new DukeException("The format of taskList.txt is incorrect");
        }
    }

    /**
     * A method to save the tasks in TaskList by
     * writing the information about the tasks to a given file.
     *
     * @param tasks An ArrayList of Task, the list of tasks to be saved.
     * @throws DukeException Exception thrown when taskList cannot be saved.
     */
    public void saveTaskList(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            int n = tasks.size();
            fw.write(String.format("%d\n", n));

            for (int i = 0; i < n; i++) {
                Task currentTask = tasks.get(i);
                if (currentTask.getTypeIcon().equals("T")) {
                    fw.write(String.format("%d ", 1));
                } else if (currentTask.getTypeIcon().equals("D")) {
                    fw.write(String.format("%d ", 2));
                } else {
                    fw.write(String.format("%d ", 3));
                }

                fw.write(currentTask.isDone() ? "1\n" : "0\n");

                fw.write(currentTask.getDescription() + "\n");

                if (currentTask instanceof Deadline) {
                    Deadline d = (Deadline) currentTask;
                    fw.write(d.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
                    if (d.getTime() != null) {
                        fw.write(d.getTime().format(DateTimeFormatter.ofPattern("HHmm")) + "\n");
                    } else {
                        fw.write("null\n");
                    }
                } else if (currentTask instanceof Event) {
                    Event e = (Event) currentTask;
                    fw.write(e.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n");
                    if (e.getTime() != null) {
                        fw.write(e.getTime().format(DateTimeFormatter.ofPattern("HHmm")) + "\n");
                    } else {
                        fw.write("null\n");
                    }
                } else {
                    fw.write("noSpecificTime\n");
                }

                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot save task list to file: " + filePath);
        }
    }
}
