import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static class DukeException extends RuntimeException {
        public DukeException(String errorMessage) {
            super("OOPS!!! " + errorMessage);
        }
    }
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;

        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[" + getStatusIcon() + "]" + " " + description);
            return sb.toString();
        }

        public void markAsDone() {
            this.isDone = true;
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by:" + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(at:" + at + ")";
        }
    }

    public static class Todo extends Task {

        protected String by;

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static void printLine() {
//        printPadding();
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void printPadding() {
        for (int i = 0; i < 5; i++) {
            System.out.print(" ");
        }
    }

    private static void printStatement(String statement) {
        System.out.println();
        printLine();
//        printPadding();
        System.out.println(statement);
        System.out.println();
        printLine();
        System.out.println();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printStatement("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> arrayList = new ArrayList<>(100);
        while (!input.equals("bye")) {
            try {
                String firstWord = input.split(" ")[0];
                if (input.equals("list")) {
                    int counter = 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Here are the tasks in your list:\n");
                    for (Task item:arrayList) {
                        sb.append(String.valueOf(counter) + ". " + item.toString() + "\n");
                        counter++;
                    }
                    printStatement(sb.toString());
                } else if (firstWord.equals("event") || firstWord.equals("deadline") || firstWord.equals("todo")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Got it. I've added this task: \n");

                    if (firstWord.equals("todo")) {
                        String message = input.substring(4);
                        if (message.equals("")) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        message = message.strip();
                        Todo newTodo = new Todo(message);
                        arrayList.add(newTodo);
                        sb.append(newTodo + "\n");
                    } else if (firstWord.equals("deadline")) {
                        String message = input.substring(8);
                        if (message.equals("")) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        message = message.strip();
                        String[] time = message.split("/by");
                        Deadline newDeadline = new Deadline(time[0], time[1]);
                        arrayList.add(newDeadline);
                        sb.append(newDeadline + "\n");
                    } else {//if (firstWord.equals("event")) {
                        String message = input.substring(5);
                        if (message.equals("")) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                        message = message.strip();
                        String[] time =message.split("/at");
                        Event newEvent = new Event(time[0], time[1]);
                        arrayList.add(newEvent);
                        sb.append(newEvent + "\n");
                    }
                    sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in da list.");
                    printStatement(sb.toString());
                }else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    String message = input.substring(4);
                    if (message.equals("")) {
                        throw new DukeException("Please indicate item to be completed.");
                    }
                    message = message.strip();
                    int number = Integer.parseInt(message) - 1;
                    Task task = arrayList.get(number);
                    task.markAsDone();
                    printStatement("Nice! I've marked this task as done:\n" + task);
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    String message = input.substring(6);
                    if (message.equals("")) {
                        throw new DukeException("Please indicate item to be deleted.");
                    }
                    message = message.strip();
                    int index = Integer.parseInt(message) - 1;
                    if (index > arrayList.size() - 1) {
                        throw new DukeException("Item does not exist.");
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("Noted. I've removed this task:\n");
                    sb.append(arrayList.get(index).toString() + "\n");
                    arrayList.remove(index);
                    System.out.println(arrayList);
                    sb.append("Now you have " + arrayList.size() + " tasks in da list.");
                    printStatement(sb.toString());


                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printStatement(e.getMessage());
            } finally {
                input = sc.nextLine();
            }

        }
        printStatement("Bye. Hope to see you again soon!");



    }
}
