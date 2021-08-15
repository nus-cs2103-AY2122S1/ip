import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Storage {

    private LinkedHashMap<String, Task> mapper;

    public Storage() {
        mapper = new LinkedHashMap<>();
    }

    public String add(String action) {
        mapper.put(action, new Task(action));
        return "added: " + action;
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
