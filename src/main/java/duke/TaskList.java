package duke;

import java.util.ArrayList;

/**
 * Contains a list of tasks.
 */
public class TaskList {
    ArrayList<Task> list;

    TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Creates a new TaskList by parsing an array of Strings into an array of tasks.
     *
     * @param stringList Arraylist of strings read from the file.
     * @throws DukeException If there is an error with parsing.
     */
    TaskList(ArrayList<String> stringList) throws DukeException {
        list = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            Task t = null;
            switch (s.charAt(1)) {
            case 'T':
                t = new Todo(s.substring(6));
                break;
            case 'D':
                s = s.substring(6);
                String[] parsedDeadline = s.split("by: ");
                t = new Deadline(parsedDeadline[0].substring(0, parsedDeadline[0].length() - 2), parsedDeadline[1].substring(0, parsedDeadline[1].length() - 1));
                break;
            case 'E':
                s = s.substring(6);
                String[] parsedEvent = s.split("at: ");
                t = new Event(parsedEvent[0].substring(0, parsedEvent[0].length() - 2), parsedEvent[1].substring(0, parsedEvent[1].length() - 1));
                break;
            default:
                throw new DukeException("File not in the correct format");
            }
            if (s.charAt(4) == 'X') {
                t.markedAsDone();
            }
            list.add(t);
        }
    }

    /**
     * Adds task to task list
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param i Index of task to be removed.
     */
    public void remove(int i){
        list.remove(i);
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the element at an index in the list.
     *
     * @param i Index number.
     * @return Task at index.
     */
    public Task get(int i) {
        return list.get(i);
    }
}
