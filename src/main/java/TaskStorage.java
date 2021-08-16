import java.util.NoSuchElementException;

public class TaskStorage {

    private Task[] storage = new Task[100];
    private int current = -1; // index for current item in the storage

    // Adds a new task to the storage and returns a confirmation message
    public String add(Task task) {
        storage[++current] = task;
        String returnString = "Got it. I've added this task:\n  "
            + task.toString() + "\n"
            + "Now you have " + (current + 1) + " tasks in the list.";
        return returnString;
    }

    // Checks if the storage is empty
    public boolean isEmpty() {
        return current == -1;
    }

    // Marks a task as done, and returns a confirmation message
    public String markDone(int ind) {
        if (ind < 0 || ind >= storage.length){ 
            throw new IllegalArgumentException("Bad index entered by user >:(");
        } else if (storage[ind] == null) {
            throw new NoSuchElementException("There is no such task! >:(");
        }
        storage[ind].markDone();

        return "Nice! I've marked this task as done:\n  " + storage[ind].toString();
    }

    // Gets a task from the storage
    public Task get(int ind) {
        if (ind < 0 || ind >= storage.length){ 
            throw new IllegalArgumentException("Bad index entered by user >:(");
        } else if (storage[ind] == null) {
            throw new NoSuchElementException("There is no such task! >:(");
        }
        return storage[ind];
    }

    // Lists out all tasks from the storage in order of index
    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < current; i++) {
            result += (String.valueOf(i + 1) + "." + storage[i].toString() + "\n");
        }
        result += (String.valueOf(current + 1) + "." + storage[current].toString());
        return result;
    }
}
