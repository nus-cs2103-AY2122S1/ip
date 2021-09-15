package duke;

import java.util.stream.Collectors;

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

    /**
     * Get response from UserInput
     *
     * @param userInput
     * @return Response including message and information
     * @throws DukeException
     */
    public static Response process(UserInput userInput) throws DukeException {
        switch (userInput.preCommand) {
        case NULL_COMMAND:
            return NOTHING.execute();
        case BYE_COMMAND:
            return BYE.execute();
        case LIST_COMMAND:
            return LIST.execute();
        case FIND_COMMAND:
            return find(userInput.bodyCommand).execute();
        case DONE_COMMAND:
            return done(userInput.bodyCommand).execute();
        case DELETE_COMMAND:
            return delete(userInput.bodyCommand).execute();
        case TODO_COMMAND:
            return add(Task.todo(userInput.bodyCommand)).execute();
        case DEADLINE_COMMAND:
            return add(Task.deadline(userInput.bodyCommand)).execute();
        case EVENT_COMMAND:
            return add(Task.event(userInput.bodyCommand)).execute();
        default:
            throw new DukeException.DukeIllegalArgumentException(userInput);
        }
    }

    /**
     * Analyze the first part of the user's command to determine the type of command
     *
     * @param command
     */
    public static UserInput analyze(String command) {
        String[] parts = command.split(" ", 2);

        // The case there is no body_command.
        if (parts.length == 1) {
            String preCommand = parts[0].equals("") ? NULL_COMMAND : parts[0];
            return new UserInput(preCommand, NULL_COMMAND);
        }

        return new UserInput(parts[0], parts[1]);
    }

    private static class Nothing extends Command {
        private Nothing() {
        }

        @Override
        protected Response execute() {
            ResponseMessage message = new ResponseMessage("Say something to me :(");
            return new Response(true, message);
        }

        @Override
        protected String getCommandName() {
            return NULL_COMMAND;
        }
    }

    private static class Bye extends Command {
        private Bye() {
        }

        @Override
        protected Response execute() {
            ResponseMessage message = new ResponseMessage("Bye. Hope to see you again soon!");
            return new Response(false, message);
        }

        @Override
        protected String getCommandName() {
            return BYE_COMMAND;
        }
    }

    private static class Add extends Command {
        private final Task t;

        private Add(Task t) {
            this.t = t;
        }

        @Override
        protected Response execute() throws DukeException.DukeEmptyNote {
            ResponseMessage responseMessage = new ResponseMessage();
            if (t.getTaskName() == NULL_COMMAND) {
                throw new DukeException.DukeEmptyNote(t.taskKind());
            }

            java.util.List<Task> matchingList = Duke.todoList
                    .stream()
                    .filter(task -> task.getTaskName().equals(this.t.getTaskName()))
                    .collect(Collectors.toList());
            if (!matchingList.isEmpty()) {
                responseMessage.appendMessage("Seem like you have already had the task with the same name:\n    "
                        + matchingList.get(0) + "\n"
                        + "Please try another task's name");
                return new Response(true, responseMessage);
            }

            t.add();
            responseMessage.appendMessage("Got it. I've added this task:\n    " + t);
            responseMessage.appendMessage("Now you have " + Duke.todoList.size() + " tasks in the list.");
            return new Response(true, responseMessage);
        }

        @Override
        protected String getCommandName() {
            return t.taskKind().toString();
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
            return new Response(true, responseMessage);
        }

        @Override
        protected String getCommandName() {
            return LIST_COMMAND;
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
            java.util.List<Task> matchingList = Duke.todoList
                    .stream()
                    .filter(task -> task.getTaskName().contains(search))
                    .collect(Collectors.toList());
            for (int i = 0; i < matchingList.size(); i++) {
                responseMessage.appendMessage(i + 1 + ". " + matchingList.get(i).toString());
            }
            return new Response(true, responseMessage);
        }

        @Override
        protected String getCommandName() {
            return FIND_COMMAND;
        }
    }

    private static class Done extends Command {
        private final String index;

        private Done(String index) {
            this.index = index;
        }

        @Override
        protected Response execute() throws DukeException.DukeIndexOutOfBoundsException,
                DukeException.DukeParseCommandException {
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                int taskIndex = Integer.parseInt(index) - 1;
                // assume that the `taskIndex` is larger than 0
                assert(taskIndex > 0);
                Task task = Duke.todoList.get(taskIndex);
                if (task.isDone()) {
                    responseMessage.appendMessage("OOPS!!! Seems like you marked the task done already:\n    " + task);
                } else {
                    // Make the task done
                    task.done();
                    responseMessage.appendMessage("Nice! I've marked this task as done:\n    " + task);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException.DukeIndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                throw new DukeException.DukeParseCommandException(this);
            }
            return new Response(true, responseMessage);
        }

        @Override
        protected String getCommandName() {
            return DONE_COMMAND;
        }
    }

    private static class Delete extends Command {
        private final String index;

        private Delete(String index) {
            this.index = index;
        }

        @Override
        protected Response execute() throws DukeException.DukeParseCommandException,
                DukeException.DukeIndexOutOfBoundsException {
            ResponseMessage responseMessage = new ResponseMessage();
            try {
                int taskIndex = Integer.parseInt(index) - 1;
                // assume that the `taskIndex` is larger than 0
                assert(taskIndex > 0);
                Task t = Duke.todoList.remove(taskIndex);
                responseMessage.appendMessage("Noted. I've removed this task:\n    " + t);
                responseMessage.appendMessage("Now you have " + Duke.todoList.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException.DukeIndexOutOfBoundsException();
            } catch (NumberFormatException e) {
                throw new DukeException.DukeParseCommandException(this);
            }

            return new Response(true, responseMessage);
        }

        @Override
        protected String getCommandName() {
            return DELETE_COMMAND;
        }
    }

    /**
     * Execute the commands
     */
    protected abstract Response execute() throws DukeException;

    protected abstract String getCommandName();
}
