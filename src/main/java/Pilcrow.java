import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Pilcrow {
    private final static String PILCROW_LOGO = """
              _____
             /   | |
            |    | |
            |    | |
             \\___| |
                 | |
                 | |
                _| |_
               |_|_|_|
            """;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Reads the text from the scanner
            String text = scanner.nextLine();
            String trimmedText = text.trim();
            String[] splitText = trimmedText.split(" ");
            ArrayList<String> words = new ArrayList<>();
            for (int i = 0; i < splitText.length; i++) {
                if (!splitText[i].equals("")) {
                    words.add(splitText[i]);
                }
            }

            // Parses the text and does the appropriate action
            Pilcrow.parseText(trimmedText, words, tasks);

            // Exit while loop if first word of trimmed text is "bye"
            if (words.size() > 0 && words.get(0).equals("bye")) {
                break;
            }
        }
    }

    private static void parseText(String trimmedText, ArrayList<String> words, ArrayList<Task> tasks) {
        String displayText = "";

        if (words.size() == 0) {
            // Error handling?
            System.out.println("Nothing entered.");
            return;
        }

        String command = words.get(0);

        // Chooses what to do based on which command is entered
        switch (command) {
            case "todo":
                // Error handling?
                String toDoName = trimmedText.substring(trimmedText.indexOf(" ") + 1);
                Task newTask = new ToDo(toDoName);
                tasks.add(newTask);
                displayText = "Added todo to tasks(" + tasks.size() + "): \n" + newTask.toString();
                break;
            case "deadline":
                // Error handling?
                String[] splitText = trimmedText.split("/");
                String deadlineName = splitText[0].trim();
                deadlineName = deadlineName.substring(deadlineName.indexOf(" ") + 1);
                String actualDeadline = splitText[1].trim();
                newTask = new Deadline(deadlineName, actualDeadline);
                tasks.add(newTask);
                displayText = "Added deadline to tasks(" + tasks.size() + "): \n" + newTask.toString();
                break;
            case "event":
                // Error handling?
                splitText = trimmedText.split("/");
                String eventName = splitText[0].trim();
                eventName = eventName.substring(eventName.indexOf(" ") + 1);
                String duration = splitText[1].trim();
                newTask = new Event(eventName, duration);
                tasks.add(newTask);
                displayText = "Added event to tasks(" + tasks.size() + "): \n" + newTask.toString();
                break;
            case "list":
                displayText = Pilcrow.printAllTasks(tasks);
                break;
            case "done":
                // Error handling?
                int doneTask = Integer.valueOf(words.get(1));
                int taskIndex = doneTask - 1;
                tasks.get(taskIndex).markAsDone(true);
                displayText = "Task " + doneTask + " set as done:\n" + tasks.get(taskIndex).toString();
                break;
            case "bye":
                displayText = "C'est fini.\n" + Pilcrow.PILCROW_LOGO;
                break;
            default:
                newTask = new Task(trimmedText);
                tasks.add(newTask);
                displayText = "Added generic task to tasks(" + tasks.size() + "): \n" + newTask.toString();
                break;
        }
        System.out.println(displayText);
    }

    private static String printAllTasks(ArrayList<Task> tasks) {
        String tasksText = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksText += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return tasksText;
    }


    // Inner classes for Task and its subclasses
    private static class Task {
        protected String taskName;
        protected Boolean isDone;

        /**
         *
         * @param taskName
         */
        public Task(String taskName) {
            this.taskName = taskName;
            this.isDone = false;
        }

        public void markAsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        /**
         *
         * @return
         */
        @Override
        public String toString() {
            String taskText = "    [" + (this.isDone ? 'X' : ' ') + "] " + taskName;
            return taskText;
        }
    }

    private static class ToDo extends Task {
        /**
         *
         * @return
         */
        public ToDo(String toDoName) {
            super(toDoName);
        }

        /**
         *
         * @return
         */
        @Override
        public String toString() {
            String toDoText = "[T] [" + (this.isDone ? 'X' : ' ') + "] " + taskName;
            return toDoText;
        }
    }

    private static class Deadline extends Task {
        String deadline;

        /**
         *
         * @param deadlineName
         * @param deadline
         */
        public Deadline(String deadlineName, String deadline) {
            super(deadlineName);
            this.deadline = deadline;
        }

        /**
         *
         * @return
         */
        @Override
        public String toString() {
            String toDoText = "[D] [" + (this.isDone ? 'X' : ' ') + "] " + taskName + " (" + this.deadline + ")";
            return toDoText;
        }
    }

    private static class Event extends Task {
        String duration;

        /**
         *
         */
        public Event(String eventName, String duration) {
            super(eventName);
            this.duration = duration;
        }

        /**
         *
         * @return
         */
        @Override
        public String toString() {
            String toDoText = "[E] [" + (this.isDone ? 'X' : ' ') + "] " + taskName + " (" + this.duration + ")";
            return toDoText;
        }
    }
}