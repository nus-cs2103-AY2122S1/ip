import java.util.ArrayList;
import java.util.regex.Pattern;

public class taskList {
    private static final Pattern TODOS = Pattern.compile("^todo\\s.+");
    private static final Pattern DEADLINES = Pattern.compile("^deadline\\s.+\\s/by\\s.+");
    private static final Pattern EVENTS = Pattern.compile("^event\\s.+\\s/at\\s.+");

    private final ArrayList<Task> lst;

    public taskList() {
        this.lst = new ArrayList<>();
    }

    public String addTask(String input) {
        input = input.trim();

        Task newTask;

        if (input.startsWith("todo")) {
            if (!TODOS.matcher(input).matches()) {
                throw new DukeException("ToDos format: todo [desc]");
            }
            newTask = new ToDos(input.substring(4));
        } else if (input.startsWith("deadline")) {
            if (!DEADLINES.matcher(input).matches()) {
                throw new DukeException("Deadline format: deadline [desc] /by [date]");
            }
            newTask = new Deadlines(input);
        } else if (input.startsWith("event")) {
            if (!EVENTS.matcher(input).matches()) {
                throw new DukeException("Event format: event [desc] /at [time]");
            }
            newTask = new Events(input);
        } else {
            throw new DukeException("Wrong format: please use todo, deadline or event");
        }

        this.lst.add(newTask);
        return String.format("Got it. I've added this task:\n    %s\nYou now have %d tasks tasks in the list.", newTask, this.lst.size());
    }

    public String deleteTask(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            Task removed = this.lst.remove(n - 1);
            return String.format("Noted. I've removed this task:\n    %s\n" +
                    "Now you have %d tasks in the list.", removed.toString(), this.lst.size());
        }
    }

    public String[] getList() {
        if (this.lst.isEmpty()) {
            throw new DukeException("The list is empty");
        }

        String[] temp = new String[this.lst.size() + 1];
        temp[0] = "Here are the tasks in your list:";

        for (int i = 1; i < this.lst.size() + 1; ++i) {
            temp[i] = i + "." + this.lst.get(i - 1).toString();
        }

        return temp;
    }

    public String markAsDone(int n) {
        if (n < 1 || n > this.lst.size()) {
            throw new DukeException("There is no task " + n);
        } else {
            return "Nice! I've marked this task as done:\n" +
                    this.lst.get(n - 1).completeTask();
        }
    }
}
