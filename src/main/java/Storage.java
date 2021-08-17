import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Storage {

    private LinkedHashMap<String, Task> mapper;

    /**
     * Basic constructor
     */
    public Storage() {
        mapper = new LinkedHashMap<>();
    }

    /**
     * Prints a basic "Added ____"
     * @param action the string that describes the task
     */
    public void basicAdd(String action) {
        mapper.put(action, new Task(action));
        Speech.speak("added: " + action);
    }

    /**
     * Identify, Search and Modify the isDone status of task based on the getStorage printed msg
     * @param input
     * @throws NumberFormatException if character after "done" is not an integer
     * @throws NullPointerException if the number provided by user is not found in the getStorage printed msg
     */
    public void done (String input) throws NumberFormatException, NullPointerException {
        int list_no = Integer.parseInt(input.trim()); //possible NumberFormatException
        Collection<Task> values = mapper.values();
        Task second = values.stream().skip(list_no-1).findFirst().orElse(null); // possible for task to be null
        String key = second.getDescription();
        mapper.put(key, mapper.get(key).setDone());
        Speech.done_msg(mapper.get(key).toString());
    }

    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input
     */
    public void todo (String input) {
        Todo todo = new Todo(input);
        mapper.put(todo.getDescription(), todo);
        Speech.task_added(todo.toString(), task_left());
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input
     */
    public void deadline (String input) {
        Deadline deadline = new Deadline(input);
        mapper.put(deadline.getDescription(), deadline);
        Speech.task_added( deadline.toString(), task_left());
    }
    /**
     * Creates a task instance, adds to storage and prints a success msg
     * @param input
     */
    public void event (String input) {
        Event event = new Event(input);
        mapper.put(event.getDescription(), event);
        Speech.task_added(event.toString(), task_left());
    }

    /**
     * Returns the number of task in the list ( does not matter if its done or not )
     * @return int
     */
    private int task_left() {
        return mapper.size();
    }

    /**
     * Prints the list in insertion order
     */
    public void getStorage() {
        Collection<Task> values = mapper.values();
        Iterator<Task> look = values.iterator();
        String[] check = new String[mapper.size()];;
        int iter_pos = 0;
        while (look.hasNext()){
            String p_pos = (iter_pos + 1) + ". ";
            check[iter_pos] = p_pos + look.next().toString();
            iter_pos++;
        }
        Speech.speak(check);
    }

}
