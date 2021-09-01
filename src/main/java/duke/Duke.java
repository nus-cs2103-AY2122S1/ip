package duke;

import duke.commands.Command;

/**
 * The Duke class that runs the Duke program.
 */
public class Duke {
    private static TaskList list;
    private static FileManager fm;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        fm = new FileManager();
        try {
            list = fm.getListFromFile();
        } catch (DukeException e) {
            list = new TaskList();
        }
    }

    /**
     * A method that gets the response from Duke to be displayed in the GUI.
     *
     * @param input User input to be responded to.
     * @return Response to be displayed.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);
        try {
            Command command = parser.parse();
            String response = command.execute(list);
            System.out.println(list.toString());
            fm.writeToFile(list);
            return response;
        } catch (DukeException e) {
            return e.getMessage(); 
        }
    }
}
