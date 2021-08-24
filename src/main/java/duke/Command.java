package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Command {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String FIND_COMMAND = "find";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    protected static final String NULL_COMMAND = "nothing";
    private static String pre_command;
    private static String body_command;

    private Command() {}

    private static class Nothing extends Command {
        private Nothing() {}

        @Override
        protected void execute() {
            System.out.println("Say something to me :(");
            Ui.printLine();
        }
    }

    private static class Bye extends Command {
        private Bye() {}

        @Override
        protected void execute() {
            System.out.println("Bye. Hope to see you again soon!");
            Ui.printLine();
        }
    }

    private static class Add extends Command {
        private final Task t;
        private Add(Task t) {
            this.t = t;
        }

        @Override
        protected void execute() {
            if (t.getTaskName() != NULL_COMMAND) {
                try {
                    t.add();
                    System.out.println("Got it. I've added this task:\n    " + t);
                    System.out.println("Now you have " + Duke.todoList.size() + " tasks in the list.");
                } catch (IOException e) {
                    System.out.println("OOPS!!! Something went wrong with adding tasks:\n    " + t);
                }

            } else {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
            }
            Ui.printLine();
        }
    }

    private static class List extends Command {
        private List() {}
        @Override
        protected void execute() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Duke.todoList.size(); i++) {
                System.out.println((i + 1) + ". " + Duke.todoList.get(i).toString());
            }
            Ui.printLine();
        }
    }

    private static class Find extends Command {
        private final String search;
        private Find(String search) {
            this.search = search;
        }
        @Override
        protected void execute() {
            System.out.println("Here are the matching tasks in your list:");
            java.util.List<Task> matchingList = new ArrayList<>();
            for (int i = 0; i < Duke.todoList.size(); i++) {
                Task t = Duke.todoList.get(i);
                if (t.getTaskName().contains(search)) {
                    matchingList.add(t);
                }
            }
            for (int i = 0; i < matchingList.size(); i++) {
                System.out.println((i + 1) + ". " + matchingList.get(i).toString());
            }
            Ui.printLine();
        }
    }

    private static class Done extends Command {
        private final String index;
        private Done(String index) {
            this.index = index;
        }

        @Override
        protected void execute() {
            try {
                Task t = Duke.todoList.get(Integer.parseInt(index) - 1);
                if (t.isDone()) {
                    System.out.println("OOPS!!! Seems like you marked the task done:\n    " + t);
                } else {
                    t.done();
                    System.out.println("Nice! I've marked this task as done:\n    " + t);
                }
                Ui.printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! I'm sorry, but I cannot find that task :(");
                Ui.printLine();
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! You need to follow format \"done <number>\"");
                Ui.printLine();
            }
        }
    }

    private static class Delete extends Command {
        private final String index;
        private Delete(String index) {
            this.index = index;
        }

        @Override
        protected void execute() {
            try {
                Task t = Duke.todoList.remove(Integer.parseInt(index) - 1);
                System.out.println("Noted. I've removed this task:\n    " + t);
                System.out.println("Now you have " + Duke.todoList.size() + " tasks in the list.");
                Ui.printLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! I'm sorry, but I cannot find that task :(");
                Ui.printLine();
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! You need to follow format \"done <number>\"");
                Ui.printLine();
            }
        }
    }

    /**
     * Handling different commands
     */
    protected static final Command NOTHING = new Nothing();
    protected static final Command BYE = new Bye();
    protected static final Command LIST = new List();
    protected static Command find(String search) {
        return new Find(search);
    }
    protected static Command done(String index) {
        return new Done(index);
    }

    protected static Command delete(String index) {
        return new Delete(index);
    }

    protected static Command add(Task t) {
        return new Add(t);
    }

    public static void process() {
        Scanner myScanner = new Scanner(System.in);

        outerLoop:
        while (true) {
            try {
                String userCommand = myScanner.nextLine();  // Read user input
                Ui.printLine();

                analyze(userCommand);

                switch (pre_command) {
                case NULL_COMMAND:
                    NOTHING.execute();
                    break;
                case BYE_COMMAND:
                    BYE.execute();
                    break outerLoop;
                case LIST_COMMAND:
                    LIST.execute();
                    break;
                case FIND_COMMAND:
                    find(body_command).execute();
                    break;
                case DONE_COMMAND:
                    done(body_command).execute();
                    break;
                case DELETE_COMMAND:
                    delete(body_command).execute();
                    break;
                case TODO_COMMAND:
                    add(Task.todo(body_command)).execute();
                    break;
                case DEADLINE_COMMAND:
                    add(Task.deadline(body_command)).execute();
                    break;
                case EVENT_COMMAND:
                    add(Task.event(body_command)).execute();
                    break;
                default:
                    throw new IllegalArgumentException("Unexpected argument: " + pre_command);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
                Ui.printLine();
            } catch (DukeException.DukeEmptyTask e) {
                System.out.println(e);
                Ui.printLine();
            } catch (DukeException.DukeEmptyNote e) {
                System.out.println(e);
                Ui.printLine();
            }
        }
    }

    /**
     * Analyze the first part of the user's command to determine the type of command
     *
     * @param command
     */
    private static void analyze(String command) {
        String[] parts = command.split(" ", 2);

        if (parts.length == 1) {
            Command.pre_command = parts[0].equals("") ? NULL_COMMAND : parts[0];
            Command.body_command = NULL_COMMAND;
        } else {
            Command.pre_command = parts[0];
            Command.body_command = parts[1];
        }
    }

    /**
     * Execute the commands
     */
    protected abstract void execute();
}
