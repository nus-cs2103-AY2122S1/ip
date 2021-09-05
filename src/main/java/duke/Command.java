package duke;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
    protected static final String NULL_COMMAND = "nothing";
    protected static final Command NOTHING = new Nothing();
    protected static final Command BYE = new Bye();
    protected static final Command LIST = new List();
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String FIND_COMMAND = "find";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

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

    public static Response process(UserInput userInput) throws DukeException.DukeEmptyTask, DukeException.DukeEmptyNote, IOException {
        switch (userInput.pre_command) {
        case NULL_COMMAND:
            return NOTHING.execute();
        case BYE_COMMAND:
            return BYE.execute();
        case LIST_COMMAND:
            return LIST.execute();
        case FIND_COMMAND:
            return find(userInput.body_command).execute();
        case DONE_COMMAND:
            return done(userInput.body_command).execute();
        case DELETE_COMMAND:
            return delete(userInput.body_command).execute();
        case TODO_COMMAND:
            return add(Task.todo(userInput.body_command)).execute();
        case DEADLINE_COMMAND:
            return add(Task.deadline(userInput.body_command)).execute();
        case EVENT_COMMAND:
            return add(Task.event(userInput.body_command)).execute();
        default:
            throw new IllegalArgumentException("Unexpected argument: " + userInput.pre_command);
        }
    }

    /**
     * Analyze the first part of the user's command to determine the type of command
     *
     * @param command
     */
    public static UserInput analyze(String command) {
        String[] parts = command.split(" ", 2);

        UserInput userInput = new UserInput();

        if (parts.length == 1) {
            userInput.pre_command = parts[0].equals("") ? NULL_COMMAND : parts[0];
            userInput.body_command = NULL_COMMAND;
        } else {
            userInput.pre_command = parts[0];
            userInput.body_command = parts[1];
        }

        return userInput;
    }

    /**
     * Execute the commands
     */
    protected abstract Response execute() throws DukeException.DukeEmptyNote, IOException;

    private static class Nothing extends Command {
        private Nothing() {
        }

        @Override
        protected Response execute() {
            ResponseMessage message = new ResponseMessage("Say something to me :(");
            message.appendNewLine();
            return new Response(true,  message);
        }
    }

    private static class Bye extends Command {
        private Bye() {
        }

        @Override
        protected Response execute() {
            ResponseMessage message = new ResponseMessage("Bye. Hope to see you again soon!");
            message.appendNewLine();
            return new Response(false, message);
        }
    }

    private static class Add extends Command {
        private final Task t;

        private Add(Task t) {
            this.t = t;
        }

        @Override
        protected Response execute() throws DukeException.DukeEmptyNote, IOException {
            ResponseMessage responseMessage = new ResponseMessage();
            if (t.getTaskName() != NULL_COMMAND) {
                    t.add();
                    responseMessage.appendMessage("Got it. I've added this task:\n    " + t);
                    responseMessage.appendMessage("Now you have " + Duke.todoList.size() + " tasks in the list.");
            } else {
                throw new DukeException.DukeEmptyNote(t.taskKind());
            }
            responseMessage.appendNewLine();
            return new Response(true, responseMessage);
        }
    }

    private static class List extends Command {
        private List() {
        }

        @Override
        protected Response execute() {
            ResponseMessage responseMessage = new ResponseMessage("Here are the tasks in your list:");
            for (int i = 0; i < Duke.todoList.size(); i++) {
                responseMessage.appendMessage(i + 1 + ". " + Duke.todoList.get(i).toString());
            }
            responseMessage.appendNewLine();
            return new Response(true, responseMessage);
        }
    }

    private static class Find extends Command {
        private final String search;

        private Find(String search) {
            this.search = search;
        }

        @Override
        protected Response execute() {
            ResponseMessage responseMessage = new ResponseMessage("Here are the matching tasks in your list:");
            java.util.List<Task> matchingList = new ArrayList<>();
            for (int i = 0; i < Duke.todoList.size(); i++) {
                Task t = Duke.todoList.get(i);
                if (t.getTaskName().contains(search)) {
                    matchingList.add(t);
                }
            }
            for (int i = 0; i < matchingList.size(); i++) {
                responseMessage.appendMessage(i + 1 + ". " + matchingList.get(i).toString());
            }
            responseMessage.appendNewLine();
            return new Response(true, responseMessage);
        }
    }

    private static class Done extends Command {
        private final String index;

        private Done(String index) {
            this.index = index;
        }

        @Override
        protected Response execute() {
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                Task t = Duke.todoList.get(Integer.parseInt(index) - 1);
                if (t.isDone()) {
                    responseMessage.appendMessage("OOPS!!! Seems like you marked the task done already:\n    " + t);
                } else {
                    t.done();
                    responseMessage.appendMessage("Nice! I've marked this task as done:\n    " + t);
                }
                responseMessage.appendNewLine();
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("OOPS!!! I'm sorry, but I cannot find that task :(\n" + Ui.printLine());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("OOPS!!! You need to follow format \"done <number>\"\n" + Ui.printLine());
            }
            return new Response(true, responseMessage);
        }
    }

    private static class Delete extends Command {
        private final String index;

        private Delete(String index) {
            this.index = index;
        }

        @Override
        protected Response execute() {
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                Task t = Duke.todoList.remove(Integer.parseInt(index) - 1);
                responseMessage.appendMessage("Noted. I've removed this task:\n    " + t);
                responseMessage.appendMessage("Now you have " + Duke.todoList.size() + " tasks in the list.");
                Ui.printLine();
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("OOPS!!! I'm sorry, but I cannot find that task :(\n" + Ui.printLine());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("OOPS!!! You need to follow format \"done <number>\"\n" + Ui.printLine());
            }

            return new Response(true, responseMessage);
        }
    }
}
