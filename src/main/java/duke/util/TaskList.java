package duke.util;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDeleteFormatException;
import duke.exceptions.InvalidDoneFormatException;
import duke.taskTypes.Deadline;
import duke.taskTypes.Event;
import duke.taskTypes.Task;
import duke.taskTypes.Todo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedHashMap;


public class TaskList {

    private final LinkedHashMap<String, Task> mapper;
    private int listLen;

    /**
     * Basic constructor
     */
    public TaskList(){
        mapper = new LinkedHashMap<>();
        listLen = 0;
    }

    public void insertPast(List<String> pastCommands) throws DukeException {

        Iterator<String> look = pastCommands.iterator();

        while (look.hasNext()){
            String[] formattedTask = look.next().split(" ", 3);
            boolean isDone = (formattedTask[1].equals("T"));
            Task temp = Task.empty();
            switch (formattedTask[0]) {
                case "T" :
                    temp = new Todo(formattedTask[2], isDone );
                    break;
                case "E" :
                    temp = new Event(formattedTask[2], isDone);
                    break;
                case "D" :
                    temp = new Deadline(formattedTask[2], isDone);
                    break;
            }
            mapper.put(temp.getDescription(), temp);
        }
    }



    /**
     * Identify, Search and Modify the isDone status of task based on the getStorage printed msg
     * @param input tajes in the input from user
     * @return String that contains the done success msg
     * @throws NumberFormatException if character after "done" is not an integer
     * @throws NullPointerException if the number provided by user is not found in the getStorage printed msg
     */
    public Task done (String input) throws InvalidDoneFormatException {
        try {
            int list_no = Integer.parseInt(input.trim()); //possible NumberFormatException
            Collection<Task> values = mapper.values();
            Task second = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
            String key = second.getDescription();
            mapper.put(key, mapper.get(key).setDone());
            return mapper.get(key);
        } catch (NumberFormatException e) {
            throw new InvalidDoneFormatException("Ensure that list position in NUMERICAL form");
        } catch (NullPointerException e) {
            throw new InvalidDoneFormatException("Ensure that number inputted can be found in the list");
        }
    }

    public Task delete(String input) throws InvalidDeleteFormatException {
        try {
            int list_no = Integer.parseInt(input.trim());
            Collection<Task> values = mapper.values();
            Task deleted = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
            String key = deleted.getDescription();
            mapper.remove(key);
            return deleted;
        } catch (NumberFormatException e) {
            throw new InvalidDeleteFormatException("Ensure that list position in NUMERICAL form");
        } catch (NullPointerException e) {
            throw new InvalidDeleteFormatException("Ensure that number inputted can be found in the list");
        }
    }

    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public Task todo (String input) throws DukeException {
        Todo todo = new Todo(input, false);
        mapper.put(todo.getDescription(), todo);
        return todo;
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public Task deadline (String input) throws DukeException {
        Deadline deadline = new Deadline(input, false);
        mapper.put(deadline.getDescription(), deadline);
        return deadline;
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public Task event (String input) throws DukeException {
        Event event = new Event(input, false);
        mapper.put(event.getDescription(), event);
        return event;
    }

    /**
     * Returns the number of task in the list ( does not matter if its done or not )
     * @return int returns the current list size
     */
    public int taskLeft() {
        return mapper.size();
    }

    /**
     * Details of the task and list them in insertion order
     * @return String that contains the details of task in list
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
     * Retyrns the number of task in the list
     * @return int
     */
    public int listMaxLen(){
        return listLen;
    }

}
