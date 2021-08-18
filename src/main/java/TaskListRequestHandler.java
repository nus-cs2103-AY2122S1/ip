import exception.BotException;
import exception.EmptyCommandException;
import exception.InvalidCommandException;
import item.*;

public class TaskListRequestHandler extends RequestHandler{
    private TaskList taskList;

    /**
     * Constructor for a TaskListRequestHandler
     * @param taskList The TaskList that the handler is responsible for.
     */
    public TaskListRequestHandler(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    /**
     * Respond to the request/
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
     * Display the list of tasks.
     */
    private void processList() {
        super.respond(taskList.display());
    }

    /**
     * Mark a task as Done.
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
     * Delete a task from the TaskList.
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
     * Add a Todo to TaskList.
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processTodo(String request) throws BotException {
        String description = request.substring("todo".length()).trim();
        super.respond(taskList.add(Todo.of(description)));
    }

    /**
     * Add a Deadline to TaskList.
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processDeadline(String request) throws BotException {
        if (!request.contains("/by") || request.split("/by").length < 2) {
            throw new InvalidCommandException("Don't cheat me, give me a due time so I can watch you >.<");
        }
        String[] inputs = request.split("deadline")[1].trim().split(" /by ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("deadline");
        }
        String description = inputs[0].trim();
        String date = inputs[1];
        respond(taskList.add(Deadline.of(description, date)));
    }

    /**
     * Add an Event to TaskList.
     * @param request The full request message from a line of input.
     * @throws BotException if the request is invalid.
     */
    private void processEvent(String request) throws BotException {
        if (!request.contains("/at") || request.split("/at").length < 2) {
            throw new InvalidCommandException("Oh no, I am not sure when this is happening >.<");
        }
        String[] inputs = request.split("event")[1].trim().split(" /at ");
        if (inputs.length == 1) {
            throw new EmptyCommandException("event");
        }
        String description = inputs[0].trim();
        String date = inputs[1];
        respond(taskList.add(Event.of(description, date)));
    }
}
