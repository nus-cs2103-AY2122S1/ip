import java.util.Arrays;
import java.util.regex.Pattern;

public class taskList {
    private static final Pattern TODOS = Pattern.compile("^todo\\s.+");
    private static final Pattern DEADLINES = Pattern.compile("^deadline\\s.+\\s/by\\s.+");
    private static final Pattern EVENTS = Pattern.compile("^event\\s.+\\s/at\\s.+");

    private final Task[] lst;
    private final String[] emptyList =
            new String[] {"The list is empty."};
    private int count;

    public taskList(int len) {
        this.lst = new Task[len];
        this.count = 0;
    }

    public String addTask(String input) {
        input = input.trim();

        // Checks if the list is full
        if (this.count >= this.lst.length) {
            return "Length of list exceeded.";
        } else {
            Task newTask;

            if (TODOS.matcher(input).matches()) {
                newTask = new ToDos(input);
            } else if (DEADLINES.matcher(input).matches()) {
                newTask = new Deadlines(input);
            } else if (EVENTS.matcher(input).matches()) {
                newTask = new Events(input);
            } else {
                newTask = new Task(input);
            }

            this.lst[this.count++] = newTask;
            return String.format("Got it. I've added this task:\n    %s\nYou now have %d tasks tasks in the list.", newTask, count);
        }
    }

    public String[] getList() {
        // Returns emptylist if the list contains no items
        if (this.count == 0) {
            return this.emptyList;
        }

        String[] temp = new String[count + 1];
        temp[0] = "Here are the tasks in your list:";

        for (int i = 1; i < this.count + 1; ++i) {
            temp[i] = i + "." + this.lst[i - 1].toString();
        }

        return temp;
    }

    public String markAsDone(int n) {
        if (n < 1 || n > count) {
            return "There is no task " + n;
        } else {
            return "Nice! I've marked this task as done:\n" +
                    this.lst[n - 1].completeTask();
        }
    }
}
