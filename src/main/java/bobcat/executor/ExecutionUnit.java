package bobcat.executor;

import java.io.IOException;
import java.util.Objects;

import bobcat.executor.command.basic.Bye;
import bobcat.executor.command.basic.Find;
import bobcat.executor.command.basic.List;
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

    /**
     * Creations an <code>ExecutionUnit</code> responsible for logic flow of BobCat. All user queries should be
     * directed to <code>ExecutionUnit</code>
     */
    public ExecutionUnit(String storagePath) {
        this.storagePath = storagePath;
        assert !Objects.equals(storagePath, null) && !Objects.equals(storagePath, "");
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
        reply = Objects.equals(queryArr[0], "list")
                ? List.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "bye")
                ? Bye.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "find")
                ? Find.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "done")
                ? Done.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "delete")
                ? Delete.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "todo")
                ? ToDo.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "deadline")
                ? Deadline.execute(taskList, queryArr)
                : Objects.equals(queryArr[0], "event")
                ? Event.execute(taskList, queryArr)
                : null;
        storeExecutor.saveStorage(storagePath, taskList);
        return reply;
    }
}
