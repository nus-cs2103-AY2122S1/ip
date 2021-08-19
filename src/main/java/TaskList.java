import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(Task task) {
        list.add(task);
        System.out.println("added: " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    public Task get(int pos) throws DukeException {
        if (pos - 1 < 0 || pos > list.size()) {
            throw new DukeException("☹ OOPS!!! No such task found!");
        }
        return list.get(pos - 1);
    }

    public int size() {
        return list.size();
    }

    public void display() {
        for (int i = 0; i < list.size(); i++) {
            String cur = list.get(i).toString();
            int label = i + 1;
            System.out.println(label + ". " + cur);
        }
    }

    public void remove(int pos) throws DukeException {
        if (pos - 1 < 0 || pos > list.size()) {
            throw new DukeException("☹ OOPS!!! No such task found!");
        }
        Task temp = list.get(pos - 1);
        list.remove(pos - 1);
        System.out.println("Noted, I have removed this task:");
        System.out.println(temp.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

}
