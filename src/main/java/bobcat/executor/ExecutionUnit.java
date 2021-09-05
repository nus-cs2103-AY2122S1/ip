package bobcat.executor;

import java.io.IOException;

import bobcat.exception.ExitException;
import bobcat.executor.command.basic.Bye;
import bobcat.executor.command.basic.Find;
import bobcat.executor.command.create.Deadline;
import bobcat.executor.command.create.Event;
import bobcat.executor.command.create.ToDo;
import bobcat.executor.command.mutate.Delete;
import bobcat.executor.command.mutate.Done;
import bobcat.executor.parser.QueryParser;
import bobcat.model.TaskList;

/**
 * Executes the logic part of the commands of BobCat
 */
public class ExecutionUnit {
    private final QueryParser parser = new QueryParser();
    private final Storage storeExecutor = new Storage();
    private TaskList taskList = new TaskList();
    private final String storagePath;

    public ExecutionUnit(String storagePath) {
        this.storagePath = storagePath;
    }
    public void initStorage() throws IOException {
        storeExecutor.initStorage(storagePath, taskList);
    }

    public void clearStorage() {
        taskList = new TaskList();
    }

    /**
     * Executes the given query. ExecutionUnit will pass the query to the appropriate parser and execute the
     * arguments according to the command recognised by the parser.
     * @param query The given query to the chat app
     * @return Array of strings representing information received after query is executed
     * @throws IOException may be thrown if given storagePath to ExecutionUnit does not exist
     */
    public String[] executeCommand(String query) throws IOException { // Very sus...
        String[] queryArr;
        queryArr = parser.parse(query);

        String[] reply;
        switch (queryArr[0]) {
        case "list":
            reply = bobcat.executor.command.basic.List.execute(taskList, queryArr);
            break;
        case "bye":
            reply = Bye.execute(taskList, queryArr);
            throw new ExitException(reply[0]);
        case "find":
            reply = Find.execute(taskList, queryArr);
            break;
        case "done":
            reply = Done.execute(taskList, queryArr);
            break;
        case "delete":
            reply = Delete.execute(taskList, queryArr);
            break;
        case "todo":
            reply = ToDo.execute(taskList, queryArr);
            break;
        case "deadline":
            reply = Deadline.execute(taskList, queryArr);
            break;
        case "event":
            reply = Event.execute(taskList, queryArr);
            break;
        default:
            reply = null;
            break;
        }
        storeExecutor.saveStorage(storagePath, taskList);
        return reply;
    }
}
