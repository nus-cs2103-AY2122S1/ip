package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a hard disk.
 */
public class Database {
    private String filePath;

    /**
     * Represents a database for Duke.
     * @param filePath for database
     */
    public Database(String filePath) {
        try {
            this.filePath = filePath;
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a hard disk.
     *
     * @return Task List
     */
    public ArrayList<Task> readData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task t = parse(taskString);
                tasks.add(t);
            }
        } catch (Exception e) {
            System.out.println("OOPS!!!Can't read data from the file\n" + e.getMessage());
        }
        return tasks;
    }

    /**
     * Adds a new task into the hard disk.
     *
     * @param task the task item added to hard disk
     */
    public void addData(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, true);

            String out = "";
            out += addTaskName(task);
            out += addDoneString(task);
            out += addPriorityString(task);
            out += addDescription(task);
            out += addTimeline(task);
            out += "\n";

            fileWriter.write(out);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Rewrite the entire file.
     *
     * @param tasks task list written into the hard disk
     */
    public void entireWriteData (ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String out;
                if (i > 0) {
                    out = "\n";
                } else {
                    out = "";
                }
                out += addTaskName(task);
                out += addDoneString(task);
                out += addPriorityString(task);
                out += addDescription(task);
                out += addTimeline(task);
                fileWriter.write(out);
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String addTaskName(Task task) {
        String taskName;
        if (task instanceof Deadline) {
            taskName = "D &";
        } else if (task instanceof Todo) {
            taskName = "T &";
        } else if (task instanceof Event) {
            taskName = "E &";
        } else {
            throw new DukeException("OOPS!!! Ths task isn't any of the three types!");
        }
        return taskName;
    }

    private String addDoneString(Task task) {
        String doneString;
        if (task.isDone()) {
            doneString = " 1 & ";
        } else {
            doneString = " 0 & ";
        }
        return doneString;
    }

    private String addPriorityString(Task task) {
        String priorityString;
        if (task.isHighPriority()) {
            priorityString = " 0 & ";
        } else if (task.isMediumPriority()) {
            priorityString = " 1 & ";
        } else if (task.isLowPriority()) {
            priorityString = " 2 & ";
        } else {
            throw new DukeException("OOPS!!! The task doesn't have a Priority tag!");
        }
        return priorityString;
    }

    private String addDescription(Task task) {
        return task.getDescription();
    }

    private String addTimeline(Task task) {
        String timeline = "";
        if (task instanceof Deadline) {
            timeline += " & " + ((Deadline) task).getBy().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
        }
        if (task instanceof Event) {
            timeline += " & " + ((Event) task).getAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hhmm"));
        }
        return timeline;
    }

    /**
     * Marks a task as Done in the hard disk.
     *
     * @param index the index of task that is marked as Done
     */
    public void doneData(int index) {
        ArrayList<Task> readOut = readData();
        Task temp = readOut.get(index);
        temp.markAsDone();
        readOut.set(index, temp);
        entireWriteData(readOut);
    }

    /**
     * Marks a task with high priority
     *
     * @param index the index of task that is marked
     */
    public void markAsHighPriority(int index) {
        ArrayList<Task> readOut = readData();
        Task temp = readOut.get(index);
        temp.markAsHighPriority();
        readOut.set(index, temp);
        entireWriteData(readOut);
    }

    /**
     * Marks a task with medium priority
     *
     * @param index the index of task that is marked
     */
    public void markAsMediumPriority(int index) {
        ArrayList<Task> readOut = readData();
        Task temp = readOut.get(index);
        temp.markAsMediumPriority();
        readOut.set(index, temp);
        entireWriteData(readOut);
    }

    /**
     * Marks a task with low priority
     *
     * @param index the index of task that is marked
     */
    public void markAsLowPriority(int index) {
        ArrayList<Task> readOut = readData();
        Task temp = readOut.get(index);
        temp.markAsLowPriority();
        readOut.set(index, temp);
        entireWriteData(readOut);
    }

    /**
     * Deletes a task from the hard disk.
     *
     * @param index the index of task that is deleted
     */
    public void deleteData(int index) {
        ArrayList<Task> readOut = readData();
        readOut.remove(index);
        entireWriteData(readOut);
    }

    /**
     * Parses a full input line into a Task object,
     * example of input line should be like: E & 0 & 1 & project meeting & 6/8/2021 1400 .
     *
     * @param string the original full command
     * @return Task
     */
    public Task parse(String string) {
        Task task;
        String[] str = string.split("&");
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].trim();
        }

        String item;
        String time;
        switch (str[0]) {
        case "T":
            item = str[3];
            task = new Todo(item);
            markDone(task, str);
            markPriority(task, str);
            break;
        case "E":
            item = str[3];
            time = str[4];
            task = new Event(item, time);
            markDone(task, str);
            markPriority(task, str);
            break;
        case "D":
            item = str[3];
            time = str[4];
            task = new Deadline(item, time);
            markDone(task, str);
            markPriority(task, str);
            break;
        default:
            task = null;
        }
        return task;
    }

    private void markDone(Task task, String[] str) {
        if (str[1].equals("1")) {
            task.markAsDone();
        }
    }

    private void markPriority(Task task, String[] str) {
        switch (str[2]) {
        case "0" :
            task.markAsHighPriority();
            break;
        case "2":
            task.markAsLowPriority();
            break;
        default:
            task.markAsMediumPriority();
        }
    }
}
