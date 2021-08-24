import java.util.ArrayList;
import java.util.List;

class TaskMapper {
    private static final String DELIMITER = "\n";

    static String makeString(Task task) {
        List<String> lines = new ArrayList<>();
        if (task instanceof ToDo) {
            lines.add(ToDo.getClassRepr());
        } else if (task instanceof Deadline) {
            lines.add(Deadline.getClassRepr());
        } else if (task instanceof Event) {
            lines.add(Event.getClassRepr());
        }
        lines.add(task.getTitle());
        if (task instanceof Deadline) {
            lines.add(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            lines.add(((Event) task).getAt());
        }
        return String.join(DELIMITER, lines);
    }

    /**
     * Returns a Task constructed based on the given information.
     *
     * @param taskRepresentation Representation of a task.
     * @return Task that is represented.
     */
    static Task makeTask(String taskRepresentation) {
        Task task = null;
        String[] lines = taskRepresentation.split(DELIMITER);
        String classRepr = lines[0];
        String title = lines[1];
        if (classRepr.equals(ToDo.getClassRepr())) {
            task = new ToDo(title);
        } else if (classRepr.equals(Deadline.getClassRepr())) {
            String by = lines[2];
            try {
                task = new Deadline(title, by);
            } catch (BlueException e) {
                System.out.println("Something when wrong when loading data from file");
                e.printStackTrace();
            }
        } else if (classRepr.equals(Event.getClassRepr())) {
            String at = lines[2];
            try {
                task = new Event(title, at);
            } catch (BlueException e) {
                System.out.println("Something when wrong when loading data from file");
                e.printStackTrace();
            }
        }
        return task;
    }
}
