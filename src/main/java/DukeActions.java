import java.util.ArrayList;
import java.util.Map;


public interface DukeActions {

    public boolean run (Map<String, String> map, ArrayList<Task> list, DukeDB database) throws DukeException;
}
