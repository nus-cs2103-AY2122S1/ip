package duke;

import java.util.ArrayList;

import duke.exceptions.NoSuchTaskException;
import duke.tasks.DeadlinesTask;
import duke.tasks.EventsTask;
import duke.tasks.FixedDurationTask;
import duke.tasks.Task;
import duke.tasks.ToDosTask;


public class Tasklist {
    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) throws NoSuchTaskException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }

    public ArrayList<Task> getTasklist() {
        return tasks;
    }

    public void doneTask(int index) throws NoSuchTaskException {
        try {
            Task temp = tasks.get(index - 1);
            temp.markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }

    public Integer getTasklistSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws NoSuchTaskException{
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }



    public static Task parseInput(String line) throws NoSuchTaskException {
        try {
            switch(line.split(" \\| ")[0]) {
            case "D":
                return new DeadlinesTask(line.split(" \\| ")[2],
                        line.split(" \\| ")[1].equals("X"), line.split(" \\| ")[3]);
            case "E":
                return new EventsTask(line.split(" \\| ")[2],
                        line.split(" \\| ")[1].equals("X"), line.split(" \\| ")[3]);
            case "T":
                return new ToDosTask(line.split(" \\| ")[2],
                        line.split(" \\| ")[1].equals("X"));
            case "F":
                return new FixedDurationTask(line.split(" \\| ")[2],
                        line.split(" \\| ")[1].equals("X"), line.split(" \\| ")[3]);
            default:
                throw new NoSuchTaskException("Invalid Line");
            }

        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }
}
