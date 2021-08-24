import java.util.Queue;
import java.util.LinkedList;

public class Duke {

    public static String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Ui userInterface = new Ui();
    public static String TASK_COLLECTION_STORAGE_PATH = "./data/duke.txt";
    private static final TaskCollection tasks = new TaskCollection(TASK_COLLECTION_STORAGE_PATH);
    private static final Queue<Action> actions = new LinkedList<>();

    public static void main(String[] args) {
        Duke.actions.add(new WelcomeUser());

        while (true) {
            try {
                if (Duke.actions.isEmpty()) {
                    String input = Duke.userInterface.getUserInput();
                    Request request = Request.create(Duke.tasks, input);
                    Duke.actions.add(request.action());
                }

                Action action = Duke.actions.remove();
                Response response = action.execute();
                Duke.tasks.saveTasks();
                Duke.userInterface.printResponse(response);

                if (action instanceof GoodbyeUser) {
                    break;
                }
            } catch (UserException exception) {
                Duke.userInterface.printResponse(exception.toResponse());
            }
        }
    }
}
