import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static class Task {

        private final String name;

        private boolean done;

        private String taskType;


        public Task(String name, String taskType) {
            this.name = name;
            done = false;
            this.taskType = taskType;
        }

        public String getName() {
            return name;
        }

        public void completeTask() {
            done = true;
        }

        public String toString() {
            return (taskType + (done ? " (done) " : " (not done) ") + name);
        }
    }

    public static class ToDo extends Task {

        public ToDo(String name) {
            super(name, "#ToDo");
        }
    }

    public static class Deadline extends Task {

        private final String deadline;

        public Deadline(String name, String deadline) {
            super(name, "#Deadline");
            this.deadline = deadline;
        }

        public String toString() {
            return super.toString() + " (" + deadline + ")";
        }
    }

    public static class Event extends Task {

        private final String time;

        public Event(String name, String time) {
            super(name, "#Event");
            this.time = time;
        }

        public String toString() {
            return super.toString() + " (" + time + ")";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        final String lineBreak = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

        List<Task> tasks = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "What can I do for you?");
        System.out.println(lineBreak);

        while (true) {
            String input = sc.nextLine();
            System.out.println(lineBreak);

            // Exit
            if (input.equals("bye")) {
                System.out.println("Duke says: Bye. Hope to see you again soon!");
                System.out.println(lineBreak);
                break;

            // List out tasks
            } else if (input.equals("list")) {
                System.out.println("Duke says: Here is your list of tasks :)");
                if (tasks.size() == 0) {
                    System.out.println("Looks like you don't have any pending tasks! Must be nice (-_-;)");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "."
                                + tasks.get(i).toString());
                    }
                }

            // Complete tasks
            } else if (input.split(" ")[0].equals("done")) {
                //TODO: add in error checking here if input is "done wpeno" or sth not int
                int taskIndex = Integer.parseInt(String.valueOf(input.charAt(5)));

                if (taskIndex > tasks.size()) {
                    System.out.println("Duke says: You don't have that many tasks!");
                } else {
                    tasks.get(taskIndex - 1).completeTask();
                    System.out.println("Duke says: You have completed the task " +
                            tasks.get(taskIndex - 1).getName() + ". Well done!");
                }

            // Add to-do task
            } else if (input.split(" ")[0].equals("todo")){
                ToDo newToDo = new ToDo(input.substring(5));
                tasks.add(newToDo);
                System.out.println("Duke says: I've added the task: ");
                System.out.println("     " + newToDo.toString());
                System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");

            // Add deadline task
            } else if (input.split(" ")[0].equals("deadline")){
                Deadline newDeadline = new Deadline(input.substring(9).split(" /")[0],
                        input.substring(9).split(" /")[1]);
                //TODO add error check if no /
                tasks.add(newDeadline);
                System.out.println("Duke says: I've added the task: ");
                System.out.println("     " + newDeadline.toString());
                System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");

            // Add event task
            } else if (input.split(" ")[0].equals("event")){
                Event newEvent = new Event(input.substring(6).split(" /")[0],
                        input.substring(6).split(" /")[1]);
                tasks.add(newEvent);
                System.out.println("Duke says: I've added the task: ");
                System.out.println("     " + newEvent.toString());
                System.out.println("You now have " + tasks.size() + " tasks, jiayouz!");

            // Add tasks
            } else {
                System.out.println("Duke says: Task '" + input + "' added");
                tasks.add(new Task(input, "#genericTask"));
            }

            //System.out.println("Duke says: " + input);
            System.out.println(lineBreak);

        }
    }
}
