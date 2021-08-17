import java.util.ArrayList;

/**
 * ToDo list stores items
 */
public class ToDo extends ArrayList<Task> {

    /**
     * ToDo constructor
     */
    public ToDo() {
        super();
    }

    public void completeTask(int index) {
        Task task = super.get(index);
        task.markAsComplete();
    }

    /**
     * @return String representation of todo list
     */
    @Override
    public String toString() {
        String stringifiedList = "";
        for (int i = 0; i < super.size(); i++) {
            stringifiedList += (i + 1) + ". " + super.get(i).toString();
            // After last item should not have line break
            if (i < super.size() - 1) {
                stringifiedList += "\n";
            }
        }
        return stringifiedList;
    }
}
