import java.util.ArrayList;

public class ToDoList {

    private final ArrayList<Task> arrayList = new ArrayList<>();

    public String add(String str) {
        this.arrayList.add(new Task(str));
        return "Added: " + str;
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
