import java.util.Scanner;
import java.lang.Exception;

public class Duke {
    public static Task[] tasks;
    public static Integer counter1 = 0;

    public static class Task {
        protected String description;
        protected boolean isDone;
        protected Integer order;

        public Task(String description) throws DukeException1 {
            if(description.equals("blah")) {
                throw new DukeException1();
            }
            this.description = description;
            this.isDone = false;
            this.order = 0;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
        //...
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) throws DukeException1{
            super(description);
            if(description.equals("deadline")) {
                throw new DukeException1();
            }
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description) throws DukeException1 {
            super(description);
            if(description.equals("todo")) {
                throw new DukeException1();
            }
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) throws DukeException1{
            super(description);
            if(description.equals("event")) {
                throw new DukeException1();
            }
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

    public static class DukeException1 extends Exception {
        DukeException1() {
            super();
        }
        @Override
        public String toString() {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
    }

    public static void main(String[] args) throws DukeException1{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String message = "Hello! I'm Duke \n" + "What can I do for you? \n";
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Task[] tasks = new Task[100];
        //String[] tasks = new String[100];
        int counter = 0;
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(("\t Here are the tasks in your list:"));
                for(Task s:tasks) {
                    if(s != null) {
                        System.out.println("\t" + Integer.toString(s.order) + "." + s.toString());
                    }
                }
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("done")) {
                Integer count = Integer.valueOf(input.split(" ")[1]);
                tasks[count - 1].markAsDone();
                System.out.println("\t Nice! I've marked this task as done: \n \t \t" +
                        " [" + tasks[count - 1].getStatusIcon() + "] " + tasks[count - 1].description);
                input = scanner.nextLine();
            } else if(input.split(" ")[0].equals("todo")) {
                try {
                    if(input.split(" ", 2).length == 1) {
                        ToDo todo = new ToDo(input.split(" ", 2)[0]);
                    } else {
                        counter = counter + 1;
                        ToDo todo = new ToDo (input.split(" ", 2)[1]);
                        todo.order = counter;
                        tasks[counter - 1] = todo;
                        System.out.println("\t" + "Got it. I've added this task: " + "\n \t \t" + todo.toString() +
                                "\n \t Now you have " + Integer.toString(counter) + " tasks in the list.");
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println("\t OOPS!!! The description of a todo cannot be empty.");
                    input = scanner.nextLine();
                }

            } else if(input.split(" ")[0].equals("deadline")) {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Deadline deadline = new Deadline(input.split(" ", 2)[0], "");
                    } else {
                        counter = counter + 1;
                        Deadline deadline = new Deadline(input.split(" ", 2)[1].split(" /")[0], input.split("/by ")[1]);
                        deadline.order = counter;
                        tasks[counter - 1] = deadline;
                        System.out.println("\t" + "Got it. I've added this task: " + "\n \t \t" + deadline.toString() +
                                "\n \t Now you have " + Integer.toString(counter) + " tasks in the list.");
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println("\t OOPS!!! The description of a deadline cannot be empty.");
                    input = scanner.nextLine();
                }
            } else if(input.equals("blah")) {
                try {
                    Task task = new Task(input);
                } catch(DukeException1 e) {
                    System.out.println("\t OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = scanner.nextLine();
                }
            } else {
                try {
                    if (input.split(" ", 2).length == 1) {
                        Event event = new Event(input.split(" ", 2)[0], "");
                    } else {
                        counter = counter + 1;
                        Event event = new Event(input.split(" ", 2)[1].split(" /")[0], input.split("/at ")[1]);
                        event.order = counter;
                        tasks[counter - 1] = event;
                        //tasks[counter - 1] = Integer.toString(counter) + ". " + input;
                        System.out.println("\t" + "Got it. I've added this task: " + "\n \t \t" + event.toString() +
                                "\n \t Now you have " + Integer.toString(counter) + " tasks in the list.");
                        input = scanner.nextLine();
                    }
                } catch(DukeException1 e) {
                    System.out.println("\t OOPS!!! The description of a event cannot be empty.");
                    input = scanner.nextLine();
                }
            }
        }
        //String ending = "Bye. Hope to see you again soon!";
        //System.out.println("\t" + ending);
        //System.out.println("does this work?");
        System.out.println("\t Bye. Hope to see you again soon!");
    }
}
