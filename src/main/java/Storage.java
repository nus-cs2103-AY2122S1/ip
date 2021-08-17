import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Storage {

    private final LinkedHashMap<String, Task> mapper;

    /**
     * Basic constructor
     */
    public Storage() {
        mapper = new LinkedHashMap<>();
    }

    /**
     * Prints a basic "Added ____"
     * @param action the string that describes the task
     * @return String that contains the message output of adding task
     */
    public String basicAdd(String action) {
        mapper.put(action, new Task(action));
        return "added: " + action;
    }

    /**
     * Identify, Search and Modify the isDone status of task based on the getStorage printed msg
     * @param input tajes in the input from user
     * @return String that contains the done success msg
     * @throws NumberFormatException if character after "done" is not an integer
     * @throws NullPointerException if the number provided by user is not found in the getStorage printed msg
     */
    public String done (String input) throws NumberFormatException, NullPointerException {
        int list_no = Integer.parseInt(input.trim()); //possible NumberFormatException
        Collection<Task> values = mapper.values();
        Task second = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
        String key = second.getDescription();
        mapper.put(key, mapper.get(key).setDone());
        return mapper.get(key).toString();
    }

    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public String todo (String input) {
        Todo todo = new Todo(input);
        mapper.put(todo.getDescription(), todo);
        return todo.toString();
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public String deadline (String input) {
        Deadline deadline = new Deadline(input);
        mapper.put(deadline.getDescription(), deadline);
        return deadline.toString();
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input tajes in the input from user
     * @return String that contains the success msg
     */
    public String event (String input) {
        Event event = new Event(input);
        mapper.put(event.getDescription(), event);
        return event.toString();
    }

    /**
     * Returns the number of task in the list ( does not matter if its done or not )
     * @return int returns the current list size
     */
    public int task_left() {
        return mapper.size();
    }

    /**
     * Details of the task and list them in insertion order
     * @return String that contains the details of task in list
     */
    public String[] getStorage() {
        Collection<Task> values = mapper.values();
        Iterator<Task> look = values.iterator();
        String[] check = new String[mapper.size()];;
        int iter_pos = 0;
        while (look.hasNext()){
            String p_pos = (iter_pos + 1) + ". ";
            check[iter_pos] = p_pos + look.next().toString();
            iter_pos++;
        }
        return check;
    }

}
