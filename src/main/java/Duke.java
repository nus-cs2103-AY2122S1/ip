import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        // constructor for task object
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatus() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            return ("[" + getStatus() + "] " + this.description);
        }
    }

    public static class Deadlines extends Task {

        protected String by;

        public Deadlines(String description, String finishBy) {
           super(description);
           this.by = finishBy;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class ToDos extends Task {

        public ToDos(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Events extends Task {

        protected String date;

        public Events(String description, String eventDate) {
            super(description);
            this.date = eventDate;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + date + ")";
        }
    }

    public static void showIntro() {
        String dory = "      _\n"
                + "     | |\n"
                + "   __| |   ___    _ __   _   _\n"
                + "  /    |  /   \\  | /__| | | | |\n"
                + " |   O | |  O  | | |    | |_| |\n"
                + "  \\__,_|  \\___/  |_|     \\__, |\n"
                + "   ________________________/  |\n"
                + "  (__________________________/\n"
                + "\n"
                + " how to use:\n"
                + "     * type down something and i'll remember\n"
                + "         'todo' followed by your 'task'\n"
                + "             eg. todo go to sleep\n"
                + "         'deadline' followed by the '/by deadline'\n"
                + "             eg. deadline finish test /by tomorrow\n"
                + "         'event' followed by the '/at time'\n"
                + "             eg. event christmas /at december\n"
                + "     * type 'list' to show everything\n"
                + "     * type 'bye' to leave\n"
                + "     * type 'done' followed by the task number\n"
                + "       to mark it as done\n"
                + " >";

        String fish = "                              ....\n"
                + "                             /.... \\\n"
                + "   hi my name is dory    .-`\\ \\   `...')\n"
                + "   and i can help you   ( o   | |      (\n"
                + "    remember things      `-_ / /_./``-._)\n"
                + "                             `\\___\\";

        // introduction to chat bot
        System.out.println(fish);
        System.out.println(dory);
    }

    public static void startChatBot() throws DukeException {
        // creates a new Scanner instance
        // System.in is the keyboard input
        Scanner input = new Scanner(System.in);

        // arraylist to save the user's tasks
        ArrayList<Task> tasks = new ArrayList<>();

        while (input.hasNextLine()) {
            // reads user input and modifies it to lower case
            String beforeEdit = input.nextLine();
            String nextInput = beforeEdit.toLowerCase();
            System.out.println("------------------------------------");

            if (nextInput.equals("bye")) {
                System.out.println(" > see you! hope to see you again :-)");
                System.out.println("==================================Oo");
                input.close();
                break;

            } else if (nextInput.equals("list")) {

                if (tasks.size() == 0) {
                    System.out.println("add anything using 'todo', 'deadline' or 'event'");
                } else {
                    System.out.println(" > here you go!");
                    // loop through the arraylist to show everything
                    for (int count = 0; count < tasks.size(); count++) {
                        Task eachTask = tasks.get(count);
                        int countFromOne = count + 1;
                        System.out.println(countFromOne + ". " + eachTask.toString());
                    }
                }
                System.out.println("==================================Oo");

            } else if (nextInput.contains("done")) {
                // to extract number from the input
                String taskToBeMarked = nextInput.replaceAll("[^0-9]", "");
                int numToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
                tasks.get(numToBeMarked).markAsDone();
                System.out.println(" > i've marked this as done:");
                System.out.println("  " + tasks.get(numToBeMarked).toString());
                System.out.println("==================================Oo");

            } else if (nextInput.contains("delete")) {
                // to extract number from the input
                String taskToBeRemoved = nextInput.replaceAll("[^0-9]", "");
                System.out.println("the num is " + taskToBeRemoved);
                int numToBeRemoved = Integer.parseInt(taskToBeRemoved) - 1;
                Task removed = tasks.get(numToBeRemoved);
                tasks.remove(numToBeRemoved);
                System.out.println(" > i've removed this task:");
                System.out.println("  " + removed.toString());
                System.out.println("you have " + tasks.size() + " things in your list");
                System.out.println("==================================Oo");
            } else {
                // add the task to the arraylist of tasks
                if (nextInput.contains("todo")) {
                    String updatedTask = nextInput.replace("todo", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new ToDoException("please add a description to your todo task");
                    }
                    System.out.println("adding " + nextInput + " final : " + updatedTask);
                    ToDos toDoTask = new ToDos(updatedTask);
                    tasks.add(toDoTask);
                    System.out.println(" > added:");
                    System.out.println("    " + toDoTask.toString());

                } else if (nextInput.contains("deadline")) {
                    String updatedTask = nextInput.replace("deadline", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new DeadLineException("please add a description to your deadline task");
                    }

                    String deadlineToAdd = updatedTask.split("/by ")[0].trim();
                    String finishBy = updatedTask.split("/by ")[1].trim();

                    Deadlines deadlineTask = new Deadlines(deadlineToAdd, finishBy);
                    tasks.add(deadlineTask);
                    System.out.println(" > added:");
                    System.out.println("    " + deadlineTask.toString());

                } else if (nextInput.contains("event")) {
                    String updatedTask = nextInput.replace("event ", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new EventException("please add a description to your event");
                    }

                    String eventToAdd = updatedTask.split("/at ")[0].trim();
                    String dateOfEvent = updatedTask.split("/at ")[1].trim();

                    Events eventTask = new Events(eventToAdd, dateOfEvent);
                    tasks.add(eventTask);
                    System.out.println(" > added:");
                    System.out.println("    " + eventTask.toString());

                } else {
                    throw new DukeException("i'm not sure i know what you mean :-( try typing something " +
                            "using 'todo', 'deadline' or 'event'");
                }

                if (tasks.size() == 1) {
                    System.out.println("you have " + tasks.size() + " thing in your list");
                } else if (tasks.size() > 1) {
                    System.out.println("you have " + tasks.size() + " things in your list");
                } else {
                    System.out.println("add anything using 'todo', 'deadline' or 'event'");
                }

                System.out.println("==================================Oo");
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        // displays logo and instructions
        showIntro();

        // starts scanner and listens for input
        startChatBot();
    }

}

