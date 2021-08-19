import java.util.ArrayList;
import java.util.regex.Pattern;
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
            // Reads the text and does the appropriate action
            Pilcrow.parseText(text, tasks);

            // Exit while loop if text is "bye"
            if (text.equals("bye")) {
                break;
            }
        }
    }

    private static void parseText(String text, ArrayList<Task> tasks) {
        // Gets command from text entered if text contains command
        String command = Pilcrow.getCommand(text);

        // Chooses what to do based on which command is entered
        switch (command) {
            case "list":
                System.out.println(Pilcrow.printAllTasks(tasks));
                break;
            case "done":
                int doneTask = Integer.valueOf(text.split(" ")[1]) - 1;
                tasks.get(doneTask).markAsDone(true);
                System.out.println("Task " + doneTask + " set as done: " + tasks.get(doneTask).toString());
                break;
            case "bye":
                System.out.println("C'est fini.\n" + Pilcrow.PILCROW_LOGO);
                break;
            default:
                Task newTask = new Task(text);
                tasks.add(newTask);
                text = "Added: \n" + text;
                System.out.println(text);
                break;
        }
    }

    // Returns the first word of the text entered as the command
    private static String getCommand(String text) {
        String command;
        if (text.contains(" ")) {
            command = text.substring(0, text.indexOf(' '));
        } else {
            command = text;
        }
        return command;
    }

    private static String printAllTasks(ArrayList<Task> tasks) {
        String tasksText = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksText += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return tasksText;
    }

    private static class Task {
        private String taskName;
        private Boolean isDone;

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
            String taskText = "[" + (this.isDone ? 'X' : ' ') + "] " + taskName;
            return taskText;
        }
    }
}

