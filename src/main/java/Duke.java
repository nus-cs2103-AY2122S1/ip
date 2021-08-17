import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static abstract class Task {
        private String task;
        private boolean isDone;

        private String isDone() {
            return (this.isDone ? "X" : " ");
        }

        private void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            String str = String.format("[%s] %s", this.isDone(), this.task);
            return str;
        }
    }

    private static class Todo extends Task{
        public Todo(String input) throws DukeIllegalInputException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeIllegalInputException("Insufficient input received! Please add in description of the Todo task.");
            }
            int tFirst = input.indexOf(" ");
            String tTask = input.substring(tFirst + 1);
            super.task = tTask;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    private static class Deadline extends Task {
        private String by;

        public Deadline(String input) throws DukeIllegalInputException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeIllegalInputException("Insufficient input received! Please add in description of the Deadline task.");
            }
            if (!input.contains("/by")) {
                throw new DukeIllegalInputException("Invalid input! Please add in the deadline for the task.");
            }
            int dFirst = input.indexOf(" ");
            int dSecond = input.indexOf("/");
            String dTask = input.substring(dFirst + 1, dSecond - 1);
            String by = input.substring(dSecond + 4);
            super.task = dTask;
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    private static class Event extends Task {
        private String at;

        public Event(String input) throws DukeIllegalInputException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeIllegalInputException("Insufficient input received! Please add in description of the Event task.");
            }
            if (!input.contains("/at")) {
                throw new DukeIllegalInputException("Invalid input! Please add in information about the event.");
            }
            int eFirst = input.indexOf(" ");
            int eSecond = input.indexOf("/");
            String eTask = input.substring(eFirst + 1, eSecond - 1);
            String at = input.substring(eSecond + 4);
            super.task = eTask;
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    private static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    private static class DukeIllegalInputException extends DukeException {
        public DukeIllegalInputException(String message) {
            super(message);
        }
    }

    private static class ToDoList {
        private ArrayList<Task> list;
        private boolean open;

        public ToDoList() {
            this.list = new ArrayList<>();
        }

        public void open() {
            this.open = true;
            System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        }

        public void exit() {
            this.open = false;
            System.out.println("Bye. Hope to see you again soon!");
        }

        public boolean isOpen() {
            return this.open;
        }

        public void add(String input, int i) {
            try {
                Task task;
                switch (i) {
                    case 1:
                        task = new Todo(input);
                        break;
                    case 2:
                        task = new Deadline(input);
                        break;
                    case 3:
                        task = new Event(input);
                        break;
                    default:
                        throw new DukeIllegalInputException("Not a valid Task!!");
                }
                list.add(task);
                System.out.println("Got it. I've added this task:\n" + "  " + task + "\n" +
                        "Now you have " + list.size() + " tasks in the list.");
            } catch (DukeIllegalInputException e) {
                System.out.println(e.getMessage());
            }
        }

        public void showList() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                String s = String.format("%d.%s", i + 1, list.get(i));
                System.out.println(s);
            }
        }

        public void markAsDone(String input) throws DukeIllegalInputException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeIllegalInputException("Insufficient input received! Please indicate the task number of the completed task.");
            }
            try {
                int index = Integer.parseInt(inputs[1]);
                list.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + list.get(index - 1));
            } catch (NumberFormatException e) {
                throw new DukeIllegalInputException("Invalid input! Please enter the task number after 'done'.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeIllegalInputException("Invalid task number! The given task number does not exist, please enter a valid task number.");
            }
        }

        public void delete(String input) throws DukeIllegalInputException {
            String[] inputs = input.split(" ");
            if (inputs.length == 1) {
                throw new DukeIllegalInputException("Insufficient input received! Please indicate the task number of the task you wish to delete.");
            }
            try {
                int index = Integer.parseInt(inputs[1]);
                Task removed = list.remove(index - 1);
                System.out.println("Noted. I've removed this task:\n  " + removed + "\nNow you have " + list.size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                throw new DukeIllegalInputException("Invalid input! Please enter the task number after 'delete'.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeIllegalInputException("Invalid task number! The given task number does not exist, please enter a valid task number.");
            }
        }
    }
    public static void main(String[] args) {
        ToDoList tdl = new ToDoList();
        Scanner sc = new Scanner(System.in);
        tdl.open();
        while (tdl.isOpen()) {
            String input = sc.nextLine().strip();
            String[] inputs = input.split(" ");
            String action = inputs[0];
            switch (action.toLowerCase()) {
                case "bye":
                    tdl.exit();
                    break;
                case "list":
                    tdl.showList();
                    break;
                case "done":
                    try {
                        tdl.markAsDone(input);
                    } catch (DukeIllegalInputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        tdl.delete(input);
                    } catch (DukeIllegalInputException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    tdl.add(input, 1);
                    break;
                case "deadline":
                    tdl.add(input, 2);
                    break;
                case "event":
                    tdl.add(input, 3);
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
        sc.close();
    }
}
