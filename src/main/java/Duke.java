import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> storage = new ArrayList<Task>();

    public static void main(String[] args) {
        print("Hello! My name is Alexa \nHow can I help you today?");
        run();
    }

    public static void print(String text) {
        System.out.println("=======================================");
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println("=======================================");
    }

    public static void run() {
        Scanner newInput = new Scanner(System.in);
        while(newInput.hasNextLine()) {
            String input = newInput.nextLine();
            Task currentTask = new Task(input);
            try {
                if (input.equals("bye")) {
                    print("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.length() >= 5 && input.substring(0, 5).equals("done ")) {
                    done(input);
                } else if (input.length() >= 5 && input.substring(0, 5).equals("todo ")) {
                    todo(input);
                } else if (input.length() >= 9 && input.substring(0, 9).equals("deadline ")) {
                    deadline(input);
                } else if (input.length() >= 6 && input.substring(0, 6).equals("event ")) {
                    event(input);
                } else if (input.length() >= 6 && input.substring(0, 7).equals("delete ")) {
                    delete(input);
                } else {
                    invalidInput();
                }
            } catch (DukeException err){
                print(err.getMessage());
            }
        }
    }

    public static void list() {
        int len = storage.size();
        String sentence = "";
        for (int i = 1; i < len + 1; i++) {
            Task currentTask = storage.get(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        print(sentence);
    }

    public static void done(String doneEntry) throws DukeException {
        int taskNumber = Integer.parseInt(doneEntry.substring(5,6));
        if (taskNumber > storage.size()) {
            throw new DukeException("Sorry ☹, please enter a valid task to complete!");
        }
        Task doneTask = storage.get(taskNumber - 1);
        doneTask.markAsDone();
        print("Congratulations on finishing this task!\n [X] " + doneTask.getDescription());
    }

    public static void todo(String todoEntry) throws DukeException {
        if (todoEntry.length() == 5) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        String todoTitle = todoEntry.substring(5);
        Todo newToDo = new Todo(todoTitle);
        storage.add(newToDo);
        print("Alright. I'm adding this task:\n  " + newToDo.toString() + "\nNow there are " + storage.size()
                + " tasks in the list" );
    }

    public static void deadline(String deadlineEntry) throws DukeException {
        if (deadlineEntry.length() == 9) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        int indexOfSlash = deadlineEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry ☹, please enter a deadline!");
        }
        String deadlineDate = deadlineEntry.substring(indexOfSlash + 3);
        String deadlineTitle = deadlineEntry.substring(9, indexOfSlash);
        Deadline newDeadline = new Deadline(deadlineTitle, deadlineDate);
        storage.add(newDeadline);
        print("Alright. I'm adding this task:\n  " + newDeadline.toString() + "\nNow there are " + storage.size()
                + " tasks in the list" );
    }

    public static void event(String eventEntry) throws DukeException {
        if (eventEntry.length() == 6) {
            throw new DukeException("Sorry ☹, please enter a description!");
        }
        int indexOfSlash = eventEntry.indexOf("/");
        if (indexOfSlash == -1) {
            throw new DukeException("Sorry ☹, please enter an event time!");
        }
        String eventDate = eventEntry.substring(indexOfSlash + 3);
        String eventTitle = eventEntry.substring(6, indexOfSlash);
        Event newEvent = new Event(eventTitle, eventDate);
        storage.add(newEvent);
        print("Alright. I'm adding this task:\n  " + newEvent.toString() + "\nNow there are " + storage.size()
                + " tasks in the list" );
    }

    public static void invalidInput() throws DukeException {
        throw new DukeException("Sorry ☹, please enter a valid command!");
    }

    public static void delete(String deleteInput) throws DukeException {
        int taskNumber = Integer.parseInt(deleteInput.substring(7,8));
        if (taskNumber > storage.size()) {
            throw new DukeException("Sorry ☹, please enter a valid task to delete!");
        }
        Task deletedTask = storage.remove(taskNumber - 1);
        print("Okay! I have deleted the task for you.\n  " + deletedTask.toString()
                + "\nNow there are " + storage.size() + " tasks in the list");
    }

}
