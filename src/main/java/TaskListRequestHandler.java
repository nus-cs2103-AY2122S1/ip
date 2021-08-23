import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.Deadline;
import item.Event;
import item.TaskList;
import item.Todo;

import java.time.LocalDate;

public class TaskListRequestHandler extends RequestHandler{
    private final TaskList taskList;

    /**
     * Constructs a TaskListRequestHandler.
     *
     * @param taskList The TaskList that the handler is responsible for.
     */
    public TaskListRequestHandler(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    public void restoreTask(String rawTask) {
        String[] input = rawTask.split(" \\| ", 4);
        String type = input[0];
        int isDone = Integer.parseInt(input[1]);
        String description = input[2];
        switch(type) {
        case "D":
            try {
                Deadline deadline = Deadline.of(description, input[3]);
                if (isDone == 1) { deadline.markAsDone(); }
                taskList.add(deadline);
            } catch (EmptyCommandException e){
                respond(e.getMessage());
            }
            break;
        case "E":
            try {
                Event event = Event.of(description, input[3]);
                if (isDone == 1) { event.markAsDone(); }
                taskList.add(event);
            } catch (EmptyCommandException e){
                respond(e.getMessage());
            }
            break;
        case "T":
            try {
                Todo todo = Todo.of(description);
                if (isDone == 1) { todo.markAsDone(); }
                taskList.add(todo);
            } catch (EmptyCommandException e){
                respond(e.getMessage());
            }
            break;
        }
    }

    public String backUpTask() {
        return taskList.toStringInDoc();
    }

    /**
     * Responds to the request.
     *
     * @param command The action word at the start of request.
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    @Override
    public void decideResponse(Command command, String request) throws BotException {
        switch (command) {
        case LIST:
            processList();
            break;
        case DONE:
            processDone(request);
            break;
        case DELETE:
            processDelete(request);
            break;
        case TODO:
            processTodo(request);
            break;
        case DEADLINE:
            processDeadline(request);
            break;
        case EVENT:
            processEvent(request);
            break;
        default:
            throw new InvalidCommandException();
        }

    }

    /**
     * Displays the list of tasks.
     */
    private void processList() {
        super.respond(taskList.display());
    }

    /**
     * Mark a task as Done.
     *
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processDone(String request) throws BotException {
        try {
            int index = Integer.parseInt(request.substring("done".length()).trim());
            respond(taskList.completeTask(index));
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processDelete(String request) throws BotException {
        try {
            int index = Integer.parseInt(request.substring("delete".length()).trim());
            respond(taskList.deleteTask(index));
        } catch(NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /**
     * Adds a Todo to TaskList.
     *
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processTodo(String request) throws BotException {
        String description = request.substring("todo".length()).trim();
        super.respond(taskList.add(Todo.of(description)));
    }

    /**
     * Adds a Deadline to TaskList.
     *
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processDeadline(String request) throws BotException {
        if (!request.contains("/by") || request.split("/by").length < 2) {
            throw new InvalidCommandException(
                    "Don't cheat me, give me a due time so I can watch you >.<");
        }
        String[] inputs = request.split("deadline")[1].trim().split(" /by ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("deadline");
        }
        String description = inputs[0].trim();
        LocalDate date = LocalDate.parse(inputs[1]);
        respond(taskList.add(Deadline.of(description, date)));
    }

    /**
     * Adds an Event to TaskList.
     *
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processEvent(String request) throws BotException {
        if (!request.contains("/at") || request.split("/at").length < 2) {
            throw new InvalidCommandException(
                    "Oh no, I am not sure when this is happening >.<");
        }
        String[] inputs = request.split("event")[1].trim().split(" /at ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("event");
        }
        String description = inputs[0].trim();
        LocalDate date = LocalDate.parse(inputs[1]);
        respond(taskList.add(Event.of(description, date)));
    }
}
