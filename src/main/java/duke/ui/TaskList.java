package duke.ui;

import duke.exception.DukeException;
import duke.task.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class TaskList { //TaskList class used to store the tasks and will be updated from the Command class
    private final ArrayList<Task> listArray;
    protected int count;

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
     * @throws DukeException Catches if the file content is incorrect to be parsed
     */
    public TaskList(BufferedReader file) throws IOException, DukeException {
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
     * @throws DukeException if the file content is not of the right format to be parsed
     */
    public Task parseLine(String line) throws DukeException {
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
            throw new DukeException("Your file is invalid");
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
    public void searchList(String string) {
        int newCount = 0;
        for (int i = 0; i < count; i++) {
            if (listArray.get(i).getName().contains(string)) {
                System.out.println(++newCount + ". " + listArray.get(i));
            }
        }
        if (newCount == 0) {
            System.out.println("Oh no! Seems like there was no match for your search");
        }
    }

    /**
     * Returns the list of task for the user
     */
    public void printList() {
        if (count == 0) {
            //When the list is empty
            System.out.println("The list is empty!");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(i + 1 + ". " + listArray.get(i));
            }
        }
    }
}
