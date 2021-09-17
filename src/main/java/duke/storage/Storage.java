package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class Storage {

    protected static File taskFile;
    protected String filePath;
    protected TaskList taskList;

    /**
     * Constructor for storage.
     *
     * @param filePath the file path to the text file that stores the user's tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(filePath);
        this.taskList = new TaskList();
    }

    /**
     * Returns user's list of tasks
     *
     * @return Task List
     */
    public TaskList load() {
        try {
            if (!taskFile.createNewFile()) {
                Scanner fileReader = new Scanner(taskFile);
                this.taskList = loadList(this.taskList, fileReader);
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    private Todo makeTodo(String description, char done) {
        Todo t = new Todo(description);
        if (done == '1') {
            t.markAsDone();
        }
        return t;
    }

    private Deadline makeDeadline(String[] processedInput) {
        Deadline d = new Deadline(processedInput[2], processedInput[3]);
        if (processedInput[1].equals("1")) {
            d.markAsDone();
        }
        return d;
    }

    private Event makeEvent(String[] processedInput) {
        Event ev = new Event(processedInput[2], processedInput[3]);
        if (processedInput[1].equals("1")) {
            ev.markAsDone();
        }
        return ev;
    }

    private TaskList loadList(TaskList taskList, Scanner fileReader) {
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] processedInput = line.split(" \\| ");
            if (processedInput[0].equals("T")) {
                Todo t = makeTodo(line.substring(8), line.charAt(4));
                taskList.addTask(t);
            } else if (processedInput[0].equals("D")) {
                Deadline d = makeDeadline(processedInput);
                taskList.addTask(d);
            } else {
                Event e = makeEvent(processedInput);
                taskList.addTask(e);
            }
        }
        return taskList;
    }

    /**
     * Saves the users tasks to a text file.
     *
     * @param taskList the list of user tasks.
     */
    public void save(ArrayList<Task> taskList) {
        File list = new File(this.filePath);
        list.delete();
        try {
            FileWriter writer = new FileWriter(this.filePath);
            writeToFile(taskList, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeToFile(ArrayList<Task> taskList, FileWriter writer) throws IOException {
        try {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String isDone = task.getIsDone() ? "1" : "0";
                if (task instanceof Todo) {
                    writer.write(getTodoString(task, isDone) + "\n");
                } else if (task instanceof Deadline) {
                    writer.write(getDeadlineString(task, isDone) + "\n");
                } else if (task instanceof Event) {
                    writer.write(getEventString(task, isDone) + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes new task to file.
     *
     * @param task the task to be added to the file.
     */
    public void addTask(Task task) {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            if (task instanceof Todo) {
                writer.write(getTodoString(task, "0") + "\n");
            } else if (task instanceof Deadline) {
                writer.write(getDeadlineString(task, "0") + "\n");
            } else if (task instanceof Event) {
                writer.write(getEventString(task, "0") + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String getTodoString(Task task, String isDone) {
        return "T | " + isDone + " | " + task.getDescription();
    }

    private String getDeadlineString(Task task, String isDone) {
        Deadline d = (Deadline) task;
        return "D | " + isDone + " | " + d.getDescription() + " | " + d.getBy();
    }

    private String getEventString(Task task, String isDone) {
        Event e = (Event) task;
        return "E | " + isDone + " | " + e.getDescription() + " | " + e.getAt();
    }

}
