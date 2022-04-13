package pika.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import pika.exception.PikaException;

public class TaskList { //TaskList class used to store the tasks and will be updated from the Command class
    protected int count;
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.count = 0;
    }

    /**
     * Constructor, Creates the Tasklist from the existing file.
     *
     * @param file Input existing file.
     * @throws IOException Catches if the filepath or file has issue.
     * @throws PikaException Catches if the file content is incorrect to be parsed.
     */
    public TaskList(BufferedReader file) throws IOException, PikaException {
        String line = file.readLine();
        this.tasks = new ArrayList<>(100);
        this.count = 0;
        while (line != null) {
            tasks.add(parseLine(line));
            count++;
            line = file.readLine();
        }
    }

    /**
     * Parses each line of the file and creates the task accordingly.
     *
     * @param line input line of file.
     * @return the tasks for the constructor.
     * @throws PikaException if the file content is not of the right format to be parsed.
     */
    public Task parseLine(String line) throws PikaException {
        String[] splits;
        Task t;
        splits = line.split(" \\| ");
        switch (splits[0]) {
        case "D":
            t = new Deadline(splits[2], TaskTime.unconvertDateTime(splits[3]));
            if (splits[1].equals("1")) {
                t.markAsDone();
            }
            if (splits.length == 5) {
                t.addTag(parseAsTag(splits[4]));
            }
            break;
        case "E":
            t = new Event(splits[2], TaskTime.unconvertDateTime(splits[3]));
            if (splits[1].equals("1")) {
                t.markAsDone();
            }
            if (splits.length == 5) {
                t.addTag(parseAsTag(splits[4]));
            }
            break;
        case "T":
            t = new Todo(splits[2]);
            if (splits[1].equals("1")) {
                t.markAsDone();
            }
            if (splits.length == 4) {
                t.addTag(parseAsTag(splits[3]));
            }
            break;
        default:
            throw new PikaException("Your file format is invalid");
        }
        return t;
    }

    /**
     * Parses the line for tags into an arrayList of tags.
     *
     * @param line The line of tags from the laoded txt file.
     * @return The arrayList of tags to be added to the task.
     */
    public ArrayList<Tag> parseAsTag(String line) {
        ArrayList<Tag> tags = new ArrayList<>(30);
        String[] splits = line.split("#");
        for (int i = 1; i < splits.length; i++) {
            tags.add(new Tag(splits[i]));
        }
        return tags;
    }

    /**
     * Adds the tasks to the list.
     *
     * @param t task to be added.
     */
    public void add(Task t) {
        assert t != null : "Pika Pi, this is not valid!";
        this.tasks.add(count++, t);
    }

    /**
     * Gets the task at the given index.
     *
     * @param i the index to get the task from
     * @return the task
     */
    public Task get(int i) {
        assert i != 0 : "Pika Pi, this is not valid!";
        return this.tasks.get(i);
    }

    /**
     * Gets the number of task in the list.
     *
     * @return the number of task in the list.
     */
    public int getCount() {
        return count;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param i the index that the task to be deleted is at.
     * @return the deleted task.
     */
    public Task delete(int i) {
        assert i != 0 : "Pika Pi, this is not valid!";
        this.count--;
        return this.tasks.remove(i);
    }

    /**
     * Searches the list for any task with the given string.
     *
     * @param string Input string pattern to find.
     * @return The list of task with the term in the task details.
     */
    public String searchList(String string) {
        assert string != null : "Pika Pi, this is not valid!";
        int newCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (tasks.get(i).getName().contains(string)) {
                sb.append(++newCount).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        if (newCount == 0) {
            return "Pika pi.... Seems like there was no match for your search";
        } else {
            return sb.toString();
        }
    }

    /**
     * Returns the list of task for the user.
     *
     * @return The list of task.
     */
    public String printList() {
        if (count == 0) {
            //When the list is empty
            return "The list is empty!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(i + 1)
                        .append(". ")
                        .append(tasks.get(i))
                        .append(tasks.get(i).getTags())
                        .append("\n");
            }
            return sb.toString();
        }
    }
}
