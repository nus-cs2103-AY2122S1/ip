package duke;

/**
 * Duke is a Personal Assistant Chatbot that keeps track of various tasks.
 *
 * @author Adam Oh Zhi Hong
 */
public class Duke {
    /** A class to keep track of all tasks of the Duke instance **/
    private TaskList taskList = new TaskList("data/duke.txt");

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        // Echo user input for now
        return "Duke says: " + input;
        /*
        try {
            Parser.handle(input, taskList);
            running = Parser.isRunning();
        } catch (InvalidCommandException e) {
            System.out.println("I'm afraid I don't recognise that, please try again!");
        } catch (MissingTaskNameException e) {
            System.out.println("Duke.Task name cannot be empty!");
        } catch (MissingTaskNumberException e) {
            System.out.println("Did you forget to enter your task number?");
        } catch (InvalidTaskNumberException e) {
            System.out.println("Sorry, that task does not exist!");
        } catch (MissingDeadlineException e) {
            System.out.println("When is that due? Let me know after '/by'!");
        } catch (MissingEventTimeException e) {
            System.out.println("When is the event happening? Let me know after '/at'!");
        } catch (DateTimeParseException e) {
            System.out.println("Oops, did you enter your date in yyyy-mm-dd format?");
        } catch (DukeException e) {
            UI.dukeException();
        }
        */
    }
}

