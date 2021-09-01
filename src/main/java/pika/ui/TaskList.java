package pika.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import pika.exception.PikaException;
import pika.task.Deadline;
import pika.task.Event;
import pika.task.Task;
import pika.task.TaskTime;
import pika.task.Todo;

public class TaskList { //TaskList class used to store the tasks and will be updated from the Command class
    protected int count;
    private final ArrayList<Task> listArray;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.listArray = new ArrayList<Task>(100);
        this.count = 0;
    }

    /**
     * Constructor, Creates the tasklist from the existing file
     * @param file Input exisiting file
     * @throws IOException Catches if the filepath or file has issue
     * @throws PikaException Catches if the file content is incorrect to be parsed
     */
    public TaskList(BufferedReader file) throws IOException, PikaException {
        String line = file.readLine();
        this.listArray = new ArrayList<Task>(100);
        this.count = 0;
        while (line != null) {
            listArray.add(parseLine(line));
            count++;
            line = file.readLine();
        }
    }

    /**
     * Parses each line of the file and creates the task accordingly
     * @param line input line of file
     * @return the tasks for the constructor
     * @throws PikaException if the file content is not of the right format to be parsed
     */
    public Task parseLine(String line) throws PikaException {
        String[] splits;
        Task t;
        splits = line.split(" \\| ");
        switch (splits[0]) {
        case "D":
            t = new Deadline(splits[2], TaskTime.unconvertDateTime(splits[3]));
            if (splits[1].equals("1")) {
                t.done();
            }
            break;
        case "E":
            t = new Event(splits[2], TaskTime.unconvertDateTime(splits[3]));
            if (splits[1].equals("1")) {
                t.done();
            }
            break;
        case "T":
            t = new Todo(splits[2]);
            if (splits[1].equals("1")) {
                t.done();
            }
            break;
        default:
            throw new PikaException("Your file is invalid");
        }

        return t;
    }

    /**
     * Add the tasks to the list
     * @param t task to be added
     */
    public void add(Task t) {
        this.listArray.add(count++, t);
    }

    /**
     * Gets the task at the given index
     * @param i the index to get the task from
     * @return the task
     */
    public Task get(int i) {
        return this.listArray.get(i);
    }

    /**
     * Gets the number of task in the list
     * @return the number of task in the list
     */
    public int getCount() {
        return count;
    }

    /**
     * Deletes the task at the given index
     * @param i the index that the task to be deleted is at
     * @return the deleted task
     */
    public Task delete(int i) {
        this.count--;
        return this.listArray.remove(i);
    }

    /**
     * Searches the list for any task with the given string
     * @param string Input string pattern to find
     */
    public String searchList(String string) {
        int newCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (listArray.get(i).getName().contains(string)) {
                sb.append(++newCount + ". " + listArray.get(i) + "\n");
            }
        }
        if (newCount == 0) {
            return "Pika pi.... Seems like there was no match for your search";
        } else {
            return sb.toString();
        }
    }

    /**
     * Returns the list of task for the user
     */
    public String printList() {
        if (count == 0) {
            //When the list is empty
            return "The list is empty!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append(i + 1 + ". " + listArray.get(i) + "\n");
            }
            return sb.toString();
        }
    }
}
