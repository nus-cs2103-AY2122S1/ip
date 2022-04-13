package duke;

import java.util.LinkedList;


/**
 * Code for the main skeleton of the Bot. When Duke is run, An instance of this class is created and used to run it.
 *
 */
public class GreetingBot {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * The list of tasks that belongs to the bot.
     */
    private LinkedList<Task> myList = new LinkedList<>();

    /**
     * Constructor that takes in nothing.
     */
    public GreetingBot() {
        ui = new Ui();
    }

    /**
     * method to start the bot
     * @return a Greeting in a String
     */
    public String startBot() {
        return this.ui.greet();
    }

    /**
     * Method to load up an existing list
     * @param filePath the path of the list
     * @return the message if loading failed or succeeds
     */
    public String loadList(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
            if (tasks.getList().size() == 0) {
                return this.ui.newListLoaded();
            } else {
                return this.ui.existingListLoaded();
            }
        } catch (DukeException err) {
            System.out.println(err.toString());
            return err.toString();
        }
    }

    /**
     * Method that runs the main functions of the bot.
     *
     */
    protected String exitBot() {
        if (tasks.getList().isEmpty()) {
            try {
                storage.deleteData();
                return ui.goodbye();
            } catch (DukeException err) {
                System.out.println(err.toString());
                return err.toString();
            }
        } else {
            return ui.goodbye();
        }
    }

    /**
     * Method to get the task list
     * @return the TaskList
     */
    protected TaskList getTasks() {
        return this.tasks;
    }




    /**
     * Method that reads input and decides what method to call to deal with the input.
     */
    protected String store(String input) {
        Parser parser = new Parser();
        try {
            String nextLine = input;
            int action = parser.parse(nextLine);
            if (action == 0) {
                return tasks.list();
            } else if (action == 1) {
                return exitBot();
            } else if (action == 2) {
                String message = tasks.setDone(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 3) {
                String message = tasks.deleteTask(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 4) {
                String message = tasks.newTodo(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 5) {
                String message = tasks.newDeadline(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 6) {
                String message = tasks.newEvent(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 7) {
                String message = tasks.findTask(nextLine);
                storage.updateData(tasks.getList());
                return message;
            } else if (action == 8) {
                String message = tasks.undo();
                storage.updateData(tasks.getList());
                return message;
            }

        } catch (DukeException err) {
            System.out.println(err.toString());
            return err.toString();
        }
        return tasks.getInfo();
    }






}



