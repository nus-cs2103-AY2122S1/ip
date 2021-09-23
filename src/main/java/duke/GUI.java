package duke;

/**
 * Controls the main logic of the application.
 * Performs command parsing, and handles the interaction with TaskList and
 * DataFile objects.
 *
 */
public class GUI {

    private final TaskList taskList;

    private final String DATA_FILE_PATH = "./dude_data.txt";

    /**
     * A convenience method used to start program execution from multiple points,
     * instead of only from the Launcher class.
     *
     * @param args Command line arguments supplied when launching the application, unused.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Constructor for that properly initialises the taskList field with a
     * DataFile.
     *
     */
    public GUI() {
        DataFile dataFile = new DataFile(DATA_FILE_PATH);
        taskList = new TaskList(dataFile);
    }

    /**
     * Parses user input and executes the appropriate response.
     * Initially passes the input to Parser to attempt to parse a new Task. If
     * a new Task object is returned, it is added into the taskList. If null is
     * returned instead, the command involves interacting with existing tasks,
     * and the correct response behaviour is directly implemented here. In future,
     * Command classes will be used to handle this in a more elegant fashion.
     *
     * @param str The input given by the user.
     * @return The string response to be sent to the user via the GUI interface.
     */
    public String getResponse(String str) {
        assert !str.equals("");
        Task newTask = Parser.parseInput(str);
        if (newTask == null) {
            if (str.equals("bye")) {
                return "Click the exit button to exit instead!";
            } else if (str.equals("save")) {
                taskList.save();
                return "TaskList saved!";
            } else if (str.equals("list")) {
                return taskList.list();
            } else if (str.startsWith("done")) {
                String substr = str.replaceFirst("done ", "");
                int index = Integer.parseInt(substr);
                return taskList.markDone(index);
            } else if (str.startsWith("delete")) {
                String substr = str.replaceFirst("delete ", "");
                int index = Integer.parseInt(substr);
                return taskList.delete(index);
            } else if (str.startsWith("find")) {
                String substr = str.replaceFirst("find ", "");
                return taskList.findTask(substr);
            } else {
                return "??? Unknown command!";
            }
        } else {
            return taskList.add(newTask);
        }
    }

}