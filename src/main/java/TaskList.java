import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task>{
    protected void add(List<String> list) {
        for(String s : list) {
            try {
                super.add(Parser.saveDataToTask(s));
            } catch (DukeException e) {
                Ui.printWithIndent(e.getMessage());
            }
        }
    }

    protected String[] toStringArr() {
        return super.stream()
                .map(Task::toString)
                .toArray(String[]::new);
    }
}
