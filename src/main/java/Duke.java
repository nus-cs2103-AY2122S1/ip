import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class Duke {
    private Parser parser;
    private TaskList tasks;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.parser = new Parser();
        this.tasks = new TaskList(new Storage());
    }

//    private void serve() {
//        Scanner input = new Scanner(System.in);
//
//        while (true) {
//            // Extract first word as command
//            String inputString = input.nextLine().trim();
//
//            if (inputString.equals("bye")) {
//                this.ui.print(" LIVE OUT YOUR PATHETIC LIFE, WEAKLING.");
//                break;
//            } else {
//                this.ui.print(this.parser.dispatch(tasks, inputString));
//
//            }
//            System.out.print("WHAT ELSE DO YOU WANT, INSECT?\n ");
//        }
//        input.close();
//    }

//    /**
//     * Executes Duke program.
//     * @param args Input arguments for the Duke program.
//     * @throws DukeException If there is a generic Duke Exception.
//     */
//    public static void main(String[] args) throws DukeException {
//        try {
//            Duke squirtle = new Duke();
//            squirtle.serve();
//        } catch (Exception e) {
//            throw new DukeException("ERROR WITH PROGRAM YOU FOOL");
//        }
//    }

    public String getResponse(String input) {
        return parser.dispatch(tasks, input);
    }
}
