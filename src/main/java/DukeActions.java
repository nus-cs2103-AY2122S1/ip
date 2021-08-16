import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;


public interface DukeActions {

    public boolean run (Map<String, String> map, ArrayList<Task> list) throws DukeException;
}
