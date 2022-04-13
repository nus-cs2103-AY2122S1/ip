package duke;

/**
 * Class to keep track of Tasks.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parser parser;
    private PlaceList placeList;

    /**
     * Constructor for a Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = this.storage.loadTaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.placeList = this.storage.loadPlaceList();
    }

    /**
     * Exits the program.
     */
    public String exit() {
        System.exit(0);
        return "Exiting...";
    }

    /**
     * Adds a task for Duke to track given a parsed command.
     *
     * @param t String array of parsed user input.
     * @throws DukeException Exception for wrong user inputs.
     */
    public String add(String[] t) throws DukeException {
        Task newTask;
        switch (t[0]) {
        case "todo":
            if (t[1].equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                newTask = new Todo(t);
            }
            break;
        case "deadline":
            if (t[1].equals("") || t[2].equals("")) {
                throw new DukeException("The description or deadline of a deadline cannot be empty.");
            } else {
                newTask = new Deadline(t);
            }
            break;
        case "event":
            if (t[1].equals("") || t[2].equals("")) {
                throw new DukeException("The description or scheduled time of an event cannot be empty.");
            } else {
                newTask = new Event(t);
            }
            break;
        default:
            throw new DukeException("Invalid command. Please check that your input is correct");
        }
        assert newTask != null;
        this.taskList.addTask(newTask);
        this.placeList.addPlace(newTask.location);
        this.storage.write(this.taskList, this.placeList);
        return this.ui.showAddMessage(newTask, this.taskList);
    }

    /**
     * Gets the task from duke given an index.
     *
     * @param index Index of Task.
     * @return Task of the given index.
     */
    public Task getTaskByIndex(int index) {
        return this.taskList.getTaskByIndex(index);
    }

    /**
     * Gets the place from duke given an index.
     *
     * @param index Index of Place.
     * @return Place of the given index.
     */
    public Place getPlaceByIndex(int index) {
        return this.placeList.getPlaceByIndex(index);
    }
    /**
     * Marks a task as done given its position in the list.
     *
     * @param itemNum Position of duke.Task in the list.
     */
    public String markDone(int itemNum) {
        this.taskList.markDone(itemNum - 1);
        this.storage.write(this.taskList, this.placeList);
        return this.ui.showMarkDoneMessage(this.getTaskByIndex(itemNum - 1));
    }

    /**
     * Deletes a task from the list.
     *
     * @param items Parsed delete command from user.
     * @return String for deleting task.
     * @throws DukeException Exception for wrong user inputs.
     */
    public String deleteTask(String[] items) throws DukeException {
        if (items[1].equals("")) {
            throw new DukeException("The task's index cannot be empty");
        } else {
            int itemNum = Integer.parseInt(items[1]);
            Task toBeDeleted = this.getTaskByIndex(itemNum - 1);
            assert toBeDeleted != null;
            this.taskList.deleteTask(itemNum - 1);
            this.storage.write(this.taskList, this.placeList);
            return this.ui.showDeleteTaskMessage(toBeDeleted, this.taskList);
        }
    }

    /**
     * Deletes a place from the list.
     *
     * @param items Parsed delete command from user.
     * @return String for deleting task.
     * @throws DukeException Exception for wrong user inputs.
     */
    public String deletePlace(String[] items) throws DukeException {
        if (items[1].equals("")) {
            throw new DukeException("This place's index cannot be empty");
        } else {
            int itemNum = Integer.parseInt(items[1]);
            Place toBeDeleted = this.getPlaceByIndex(itemNum - 1);
            assert toBeDeleted != null;
            this.placeList.deletePlace(itemNum - 1);
            this.storage.write(this.taskList, this.placeList);
            return this.ui.showDeletePlaceMessage(toBeDeleted, this.placeList);
        }
    }

    /**
     * Starts Duke to allow for inputs.
     */
    public void run() {
        this.storage.loadTaskList();
        String userInput;
        boolean hasExited = false;
        while (!hasExited) {
            userInput = this.ui.getUserInput();
            String[] items = this.parser.parse(userInput);
            executeInput(items);
            if (items[0] == "bye") {
                hasExited = true;
            }
        }
        this.exit();
    }

    /**
     * Executes the parsed user input.
     *
     * @param parsedInput String array of parsed user input.
     */
    public String executeInput(String[] parsedInput) {
        try {
            switch (parsedInput[0]) {
            case "bye":
                return this.exit();
            case "list":
                return this.ui.showTaskList(this.taskList);
            case "done":
                return this.markDone(Integer.parseInt(parsedInput[1]));
            case "todo":
                //Fallthrough
            case "deadline":
                //Fallthrough
            case "event":
                return this.add(parsedInput);
            case "delete-task":
                return this.deleteTask(parsedInput);
            case "delete-place":
                return this.deletePlace(parsedInput);
            case "find":
                String result = this.taskList.find(parsedInput[1]);
                return this.ui.showSearchResults(result);
            case "places":
                return this.ui.showPlaceList(this.placeList);
            default:
                throw new DukeException("Invalid input. Perhaps you've made a mistake.");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response after executing given input.
     *
     * @param input String to respond to.
     * @return String containing the response.
     */
    public String getResponse(String input) {
        return executeInput(parser.parse(input));
    }
}
