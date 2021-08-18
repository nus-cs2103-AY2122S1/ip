import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void doneTask() {
        this.isDone = true;
    }

    public static void isFirstWordValid(String input, String expectedTaskName) throws DukeException {
        String firstWord = input.split(" ", 2)[0];
        if (!firstWord.equals(expectedTaskName)) {
            throw new DukeException(firstWord, DukeException.ErrorType.INVALID_INPUT);
        }
    }

    public static boolean isDescriptionEmpty(String input) {
        String removedSpace = input.replaceAll("\\s", "");
        return removedSpace.equals(input);
    }

    public static void printList(ArrayList<Task> storeRoom) {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        int counter = 1;
        for (Task taskForLoop : storeRoom) {
            System.out.println(counter
                    + "."
                    + taskForLoop);
            counter++;
        }
        System.out.println("____________________________________________________________\n");
    }

    public void printDoneTask() {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n  "
                + this
                + "\n"
                + "____________________________________________________________\n");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public void addTask(ArrayList<Task> storeRoom) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n  "
                + this
                + "\n"
                + "Now you have "
                + storeRoom.size()
                + " tasks in the list."
                + "\n"
                + "____________________________________________________________\n");
    }
//
//    public ArrayList<Task> delete(ArrayList<Task> storeRoom, int index) {
//        storeRoom = storeRoom.remove();
//    }
}