package duke.ui;

import duke.exception.DukeException;
import duke.task.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.IOException;

public class TaskList { //TaskList class used to store the tasks and will be updated from the Command class
    private final ArrayList<Task> listArray;
    protected int count;

    public TaskList() {
        this.listArray = new ArrayList<Task>(100);
        this.count = 0;
    }

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

    public void add(Task t) {
        this.listArray.add(count++, t);
    }

    public Task get(int i) {
        return this.listArray.get(i);
    }

    public int getCount() {
        return count;
    }

    public Task delete(int i) {
        this.count--;
        return this.listArray.remove(i);
    }

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
