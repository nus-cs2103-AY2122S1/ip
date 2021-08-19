import java.util.ArrayList;

public class ToDoList {

    private final ArrayList<Task> arrayList = new ArrayList<>();

    public String addToDo(String description) {
        ToDo toDo = new ToDo(description);
        this.arrayList.add(toDo);
        return "Added this ToDo task:\n" + toDo.toString();
    }

    public String addDeadLine(String description, String deadline) {
        Deadline dl = new Deadline(description, deadline);
        this.arrayList.add(dl);
        return "Added this Deadline task:\n" + dl.toString();
    }

    public String addEvent(String description, String time) {
        Event event = new Event(description, time);
        this.arrayList.add(event);
        return "Added this Event task:\n" + event.toString();
    }

    public String list() {
        int i = 1;
        String returnString = "";
        for (Task t : this.arrayList) {
            returnString += String.valueOf(i) + ". " + t.toString() + "\n";
            i++;
        }
        return returnString;
    }

    public String markDone(int index) {
        Task t = this.arrayList.get(index - 1);
        boolean isValid = t.markDone();
        if (isValid) {
            return "Nice! This task is marked as done\n" + t.toString();
        } else {
            return "";
        }
    }

}
