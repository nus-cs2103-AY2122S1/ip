import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;


public class Storage {

    private LinkedHashMap<String, Task> mapper;

    public Storage() {
        mapper = new LinkedHashMap<>();
    }

    public String basicAdd(String action) {
        mapper.put(action, new Task(action));
        return "added: " + action;
    }

    public String done (String input) throws NumberFormatException  {
        int list_no;
        list_no = Integer.parseInt(input.trim()); //possible NumberFormatException
        Collection<Task> values = mapper.values();
        Task second = values.stream().skip(list_no-1).findFirst().orElse(null);
        String key = second.getDescription();
        mapper.put(key, mapper.get(key).setDone());
        return mapper.get(key).toString();
    }


    public String todo (String input) {
        Todo todo = new Todo(input);
        mapper.put(todo.getDescription(), todo);
        return todo.toString();
    }

    public String deadline (String input) {
        Deadline deadline = new Deadline(input);
        mapper.put(deadline.getDescription(), deadline);
        return deadline.toString();
    }

    public String event (String input) {
        Event event = new Event(input);
        mapper.put(event.getDescription(), event);
        return event.toString();
    }

    public int task_left() {
        return mapper.size();
    }

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
