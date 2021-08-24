import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    TaskList() {
        list = new ArrayList<>();
    }

    TaskList(ArrayList<String> stringList) throws DukeException {
        list = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            String s = stringList.get(i);
            Task t = null;
            switch (s.charAt(1)) {
            case 'T':
                t = new Todo(s.substring(6));
                break;
            case 'D':
                s = s.substring(6);
                String[] parsedDeadline = s.split("by: ");
                t = new Deadline(parsedDeadline[0].substring(0, parsedDeadline[0].length() - 2), parsedDeadline[1].substring(0, parsedDeadline[1].length() - 1));
                break;
            case 'E':
                s = s.substring(6);
                String[] parsedEvent = s.split("at: ");
                t = new Event(parsedEvent[0].substring(0, parsedEvent[0].length() - 2), parsedEvent[1].substring(0, parsedEvent[1].length() - 1));
                break;
            default:
                throw new DukeException("File not in the correct format");
            }
            if (s.charAt(4) == 'X') {
                t.markedAsDone();
            }
            list.add(t);
        }
    }

    public void add(Task t) {
        list.add(t);
    }

    public void remove(int i){
        list.remove(i);
    }
    public int getSize() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }
}
