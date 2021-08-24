import java.util.ArrayList;
import java.util.Map;


public interface DukeActions {

    public boolean run (Map<String, String> map, DukeTaskList list, DukeDB database, DukeDateConfig config, Ui ui) throws DukeException;
}
