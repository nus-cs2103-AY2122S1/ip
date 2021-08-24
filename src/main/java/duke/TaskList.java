package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(ArrayList<String> stringList) throws DukeException {
        tasks = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            Task t = null;
            switch (s.charAt(1)) {
            case 'T':
                t = new Todo(s.substring(7));
                break;
            case 'D':
                s = s.substring(7);
                String[] parsedDeadline = s.split("by: ");
                t = new Deadline(parsedDeadline[0].substring(0, parsedDeadline[0].length() - 2), parsedDeadline[1].substring(0, parsedDeadline[1].length() - 1));
                break;
            case 'E':
                s = s.substring(7);
                String[] parsedEvent = s.split("at: ");
                t = new Event(parsedEvent[0].substring(0, parsedEvent[0].length() - 2), parsedEvent[1].substring(0, parsedEvent[1].length() - 1));
                break;
            default:
                throw new DukeException("File not in the correct format");
            }
            if (s.charAt(4) == 'X') {
                t.markedAsDone();
            }
            tasks.add(t);
        }
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i){
        tasks.remove(i);
    }
    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void find(String s) throws DukeException {
        List<Task> filteredList = list.stream().filter(task -> task.toString().contains(s)).collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new DukeException(s + " not found in list");
        }
        listItems(filteredList);
    }

    /**
     * Lists out current items.
     */
    public void listItems() {
        listItems(this.list);
    }

    private void listItems(List<Task> tasks) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println("      " + num + "." + tasks.get(i).toString());
        }
    }
}
