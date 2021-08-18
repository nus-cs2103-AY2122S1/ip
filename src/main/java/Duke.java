import java.util.ArrayList;

public class Duke {

    private ArrayList<Task> list = new ArrayList<>();
    private static final String divider = "\t____________________________________________________________\n";

    @FunctionalInterface
    private interface CheckedConsumer<T> {
        void accept(T t) throws DukeException;
    }

    enum Action {
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        NONE
    }

    private static class DukeException extends Exception {
        private DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public void greet() {
        System.out.println(divider +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                divider);
    }

    public boolean process(String str) {
        try {
            String[] arr = str.split(" ", 2);
            if (arr.length < 1) {
                throw new DukeException("No command was given.");
            }
            String firstWord = arr[0];
            if (str.equals("bye")) {
                System.out.println(divider + "\tBye. Hope to see you again soon!\n" + divider);
                return false;
            } else if (str.equals("list")) {
                displayList();
            } else {
                Action action = parseFirstWord(firstWord);
                CheckedConsumer<String> consumer = actionToFunction(action);
              if (arr.length < 2 && action == Action.DONE) {
                    throw new DukeException("Task number for done is not given.");
                } else if (arr.length < 2 || arr[1].isBlank()) {
                    throw new DukeException("The description of " + firstWord + " cannot be empty");
                } else {
                   consumer.accept(arr[1]);
                }
            }
            return true;
        } catch (DukeException e) {
            System.out.println(divider + "\t ☹ OOPS!!! " + e.getMessage() + "\n" + divider);
            return true;
        }
    }

    private Action parseFirstWord(String firstWord) {
        switch (firstWord) {
            case "done":
                return Action.DONE;
            case "todo":
                return Action.TODO;
            case "deadline":
                return Action.DEADLINE;
            case "event":
                return Action.EVENT;
            default:
                return Action.NONE;
        }
    }


    private CheckedConsumer<String> actionToFunction(Action action) throws DukeException {
        switch (action) {
            case DONE:
                return this::done;
            case TODO:
                return this::addTodo;
            case DEADLINE:
                return this::addDeadline;
            case EVENT:
                return this::addEvent;
            case NONE:
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void done(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                list.get(i - 1).markDone();
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    private void addEvent(String str) throws DukeException {
        if (str.contains("/at ")) {
            String[] arr = str.split("/at ", 2);
            Task t = new Event(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    private void addDeadline(String str) throws DukeException {
        if (str.contains("/by ")) {
            String[] arr = str.split("/by ", 2);
            Task t = new Deadline(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }


    private void addTodo(String str) {
        Task t = new Todo(str);
        addTask(t);
    }

    private void addTask(Task t) {
        list.add(t);
        System.out.println(divider + "\tGot it. I've added this task: ");
        System.out.println("\t" + t);
        System.out.println("\tNow you have " + list.size() + " task" + (list.size() == 1 ? " " : "s ") + "in the list.");
        System.out.println(divider);
    }

    private void addList(String str) {
        Task t = new Task(str);
        list.add(t);
        System.out.println(divider + "\tadded: " + t + "\n" + divider);
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println(divider + "\tlist empty\n" + divider);
            return;
        }
        System.out.print(divider);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
        System.out.println(divider);
    }

}
