package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import duke.command.DukeException;

/**
 * This class implements a TaskList object that contains the task list and its operations.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class TaskList {
    /** Formatter to change String to a Date */
    protected static SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy hh:mm aaa");
    private List<Task> toDoList;

    /**
     * Default constructor.
     *
     * @param tasks List of tasks to be loaded.
     */
    public TaskList(ArrayList<String> tasks) {
        this.toDoList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String s = tasks.get(i);
            char type = s.charAt(1);
            switch (type) {
            case 'T':
                Todo t = new Todo(s.substring(7));
                if (s.charAt(4) == 'x') {
                    t.markAsDone();
                }
                toDoList.add(t);
                break;
            case 'D':
                int deadlineIndex = s.indexOf("(by:");
                Calendar deadlineCal = Calendar.getInstance();

                try {
                    deadlineCal.setTime(formatter.parse(s.substring(deadlineIndex + 5, s.length() - 1)));
                } catch (ParseException e) {
                    System.out.println(e);
                }

                Deadline d = new Deadline(s.substring(7, deadlineIndex), deadlineCal);
                if (s.charAt(4) == 'x') {
                    d.markAsDone();
                }
                toDoList.add(d);
                break;
            case 'E':
                int eventIndex = s.indexOf("(at:");

                Calendar eventCal = Calendar.getInstance();

                try {
                    eventCal.setTime(formatter.parse(s.substring(eventIndex + 5, s.length() - 1)));
                } catch (ParseException e) {
                    System.out.println(e);
                }

                Event event = new Event(s.substring(7, eventIndex), eventCal);
                if (s.charAt(4) == 'x') {
                    event.markAsDone();
                }
                toDoList.add(event);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Adds task to list.
     *
     * @param t Task to be added
     */
    public void add(Task t) {
        toDoList.add(t);
    }

    /**
     * Deletes task at index i.
     *
     * @param i Index of tasks to be deleted.
     */
    public Task delete(int i) throws DukeException {
        if (i > toDoList.size() || i < 1) {
            throw new DukeException("OOPS!!! Invalid task number");
        }

        return toDoList.remove(i - 1);

    }

    /**
     * Marks task at index i as done.
     *
     * @param i Index of tasks to be marked.
     */
    public void markAsDone(int i) throws DukeException {
        if (i > toDoList.size() || i < 1) {
            throw new DukeException("OOPS!!! Invalid task number");
        }
        toDoList.get(i - 1).markAsDone();
    }

    /** Lists tasks. */
    public String list() {
        String taskList = "";
        for (int i = 0; i < toDoList.size(); i++) {
            String task = (i + 1) + ". " + toDoList.get(i).toString() + "\n";
            taskList += task;
        }
        return taskList;
    }

    /**
     * Returns size of list of tasks.
     *
     * @return Size of list.
     */
    public int size() {
        return toDoList.size();
    }

    /**
     * Returns String representation of Task at index i.
     *
     * @param i Index of task to be accessed.
     * @return String representation of Task at index i.
     */
    public String getStringDes(int i) {
        if (i > toDoList.size() || i < 1) {
            return "";
        }
        return toDoList.get(i - 1).toString();
    }

    /**
     * Finds tasks that matches with inputted String
     *
     * @param s Inputted String.
     */
    public String find(String s) {
        String taskList = "";
        int k = 1;
        for (int i = 0; i < toDoList.size(); i++) {
            String desc = toDoList.get(i).toString();
            if (desc.indexOf(s) >= 0) {
                String task = k + ". " + desc + "\n";
                taskList += task;
                k++;
            }
        }
        return taskList;
    }


}
