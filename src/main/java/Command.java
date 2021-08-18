import java.util.Scanner;

public abstract class Command {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private Command() {}

    private static class Bye extends Command {
        private Bye() {}

        @Override
        protected void execute() {
            System.out.println("Bye. Hope to see you again soon!");
            Duke.printLine();
        };
    }

    private static class Add extends Command {
        private Task t;
        private Add(Task t) {
            this.t = t;
        }

        @Override
        protected void execute() {
            Duke.todoList.add(t);
            System.out.println("Got it. I've added this task:\n    " + t);
            System.out.println("Now you have" + Duke.todoList.size() + "tasks in the list.");
            Duke.printLine();
        };
    }

    private static class List extends Command {
        private List() {}
        @Override
        protected void execute() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Duke.todoList.size(); i++) {
                System.out.println((i + 1) + ". " + Duke.todoList.get(i).toString());
            }
            Duke.printLine();
        };
    }

    private static class Done extends Command {
        private int index;
        private Done(int index) {
            this.index = index;
        }

        @Override
        protected void execute() {
            Task t = Duke.todoList.get(index - 1);
            t.done();
            System.out.println("Nice! I've marked this task as done:\n    " + t);
            Duke.printLine();
        };
    }

    private static final Command BYE = new Bye();
    private static final Command LIST = new List();
    private static Command done(int index) {
        Command d = new Done(index);
        return d;
    }

    private static Command add(Task t) {
        Command a = new Add(t);
        return a;
    }

    public static void process() {
        Scanner myScanner = new Scanner(System.in);

        outerLoop:
        while (true) {
            try {
                String userCommand = myScanner.nextLine();  // Read user input
                Duke.printLine();

                String[] parts = userCommand.split(" ", 2);
                switch (parts[0]) {
                    case BYE_COMMAND:
                        BYE.execute();
                        break outerLoop;
                    case LIST_COMMAND:
                        LIST.execute();
                        break;
                    case DONE_COMMAND:
                        done(Integer.parseInt(parts[1])).execute();
                        break;
                    case TODO_COMMAND:
                        add(Task.todo(parts[1])).execute();
                        break;
                    case DEADLINE_COMMAND:
                        add(Task.deadline(parts[1])).execute();
                        break;
                    case EVENT_COMMAND:
                        add(Task.event(parts[1])).execute();
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected argument: " + parts[0]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Need to input format \"done <integer>\"");
                System.out.println(e);
                Duke.printLine();
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-()");
                Duke.printLine();
            }
        }
    }

    protected abstract void execute();
}
