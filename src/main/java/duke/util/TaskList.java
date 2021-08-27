package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidFormatException;
import duke.taskTypes.Deadline;
import duke.taskTypes.Event;
import duke.taskTypes.Task;
import duke.taskTypes.Todo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;

/**
 * contains the task list that has operations to add/delete tasks in the list
 */
public class TaskList {

    private final LinkedHashMap<String, Task> mapper;
    private int listLen;

    /**
     * Basic constructor for Task list
     */
    public TaskList(){
        mapper = new LinkedHashMap<>();
        listLen = 0;
    }

    /**
     * Insert Past state from storage into current tasklist
     *
     * @param pastTaskLists List of past task details found in the storage
     * @throws DukeException
     */
    public void insertPast(List<String> pastTaskLists) throws DukeException {

        Iterator<String> look = pastTaskLists.iterator();

        while (look.hasNext()){
            String[] formattedTask = look.next().split(" ", 3);
            boolean isDone = (formattedTask[1].equals("T"));
            Task pastTask = Task.empty();
            switch (formattedTask[0]) {
                case "T" :
                    pastTask = new Todo(formattedTask[2], isDone );
                    break;
                case "E" :
                    pastTask = new Event(formattedTask[2], isDone);
                    break;
                case "D" :
                    pastTask = new Deadline(formattedTask[2], isDone);
                    break;
            }
            mapper.put(pastTask.getDescription(), pastTask);
        }
    }



    /**
     * Identify, Search and Modify the isDone status of task based on the getStorage printed msg
     *
     * @param input tajes in the input from user
     * @return String that contains the done success msg
     * @throws InvalidFormatException when user input does not conform to the standard
     */
    public Task done (String input) throws DukeException {
        try {
            int list_no = Integer.parseInt(input.trim()); //possible NumberFormatException
            Collection<Task> values = mapper.values();
            Task second = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
            String key = second.getDescription();
            mapper.put(key, mapper.get(key).setDone());
            return mapper.get(key);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Ensure that list position in NUMERICAL form");
        } catch (NullPointerException e) {
            throw new InvalidFormatException("Ensure that number inputted can be found in the list");
        }
    }

    /**
     * Identify, Search and Delete task from task list based on the position of list
     *
     * @param input contains the position of which task to delete
     * @return Task returns deleted task
     * @throws DukeException
     */
    public Task delete(String input) throws DukeException {
        try {
            int list_no = Integer.parseInt(input.trim());
            Collection<Task> values = mapper.values();
            Task deleted = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
            String key = deleted.getDescription();
            mapper.remove(key);
            return deleted;
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Ensure that list position in NUMERICAL form");
        } catch (NullPointerException e) {
            throw new InvalidFormatException("Ensure that number inputted can be found in the list");
        }
    }

    /**
     * Creates a task instance, adds to storage and prints a success msg
     *
     * @param input takes in the input from user
     * @return Task returns newly created task
     */
    public Task todo (String input) throws DukeException {
        Todo todo = new Todo(input, false);
        mapper.put(todo.getDescription(), todo);
        return todo;
    }

    /**
     * Creates a task instance, adds to storage and prints a success msg
     *
     * @param input tajes in the input from user
     * @return Task returns newly created task
     */
    public Task deadline (String input) throws DukeException {
        Deadline deadline = new Deadline(input, false);
        mapper.put(deadline.getDescription(), deadline);
        return deadline;
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     *
     * @param input tajes in the input from user
     * @return Task returns newly created task
     */
    public Task event (String input) throws DukeException {
        Event event = new Event(input, false);
        mapper.put(event.getDescription(), event);
        return event;
    }

    /**
     * Returns the number of task in the list ( does not matter if its done or not )
     *
     * @return int returns the current list size
     */
    public int taskLeft() {
        return mapper.size();
    }

    /**
     * Details of the task and list them in insertion order
     *
     * @return String array that contains the details
     */
    public String[] getList() {
        if (mapper.size() == 0) {
            return new String[]{"Empty List"};
        }
        int maxLen = 0;
        Collection<Task> values = mapper.values();
        Iterator<Task> look = values.iterator();
        String[] check = new String[mapper.size()];;
        int pos = 0;
        while (look.hasNext()){
            String listPos = pos+1 + ". ";
            check[pos] = listPos + look.next().toString();
            maxLen = Math.max(maxLen, check[pos].length());
            pos++;
        }
        listLen = maxLen;
        return check;
    }

    /**
     * Returns the details on the current state as a string array
     *
     * @return String[] Array of sring that contains descrptions of state
     */
    public String[] saveState() {
        if (mapper.size() == 0) {
            return new String[]{};
        }
        Collection<Task> values = mapper.values();
        Iterator<Task> look = values.iterator();
        String[] check = new String[mapper.size()];;
        for (int pos = 0; pos < mapper.size(); pos++) {
            check[pos] = look.next().saveTask();
        }
        return check;
    }

    /**
     * Retyrns the number of task currently in the list
     *
     * @return int
     */
    public int listMaxLen() {
        return listLen;
    }

}
