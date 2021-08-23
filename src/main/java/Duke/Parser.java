public class Parser {
    private final ToDoList dukeList;
    private final Data data;

    public Parser(ToDoList dukeList, Data data) {
        this.dukeList = dukeList;
        this.data = data;
    }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param input User's input
     * @return The correct command that is interpreted from the user input.
     */
    public static Command inputToCommand(String input) {
        String[] inputs = input.split(" ", 2);
        return Command.readInput(inputs[0]);
    }

    /**
     * Adds a Deadline to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "deadline Whatever /by Whenever".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addDeadline(String input) throws DukeException{
        // First check if the user has only input the one word "deadline".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the deadline date! Try again :-)");
        }
        // If "deadline" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/by ", 2);

        //In the case where date is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the deadline date! Try again :-)");
        }
        Deadline newDL = new Deadline(description[0], description[1]);
        dukeList.add(newDL);
        Data.writeToFile(newDL);
        System.out.println(UI.addedText + newDL.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }

    /**
     * Adds an Event to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "event Here /at There".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addEvent(String input) throws DukeException{
        // First check if the user has only input the one word "event".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description and the event location! Try again :-)");
        }
        // If "event" is entered with more words, check information.
        String[] information = input.split(" ", 2);
        String [] description = information[1].split("/at ", 2);

        //In the case where location is not entered.
        if (description.length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the event location! Try again :-)");
        }
        Event newEV = new Event(description[0], description[1]);
        dukeList.add(newEV);
        Data.writeToFile(newEV);
        System.out.println(UI.addedText + newEV.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }

    /**
     * Adds a Todo to all Tasks that Duke has stored.
     * @param input The entire String that the user has input i.e. "todo This task".
     * @throws DukeException If an incorrect input is entered.
     */
    public void addTodo(String input) throws DukeException {
        // First check if the user has only input the one word "todo".
        if (input.split(" ", 2).length == 1) {
            throw new DukeException("☹ Oops! Looks like you are missing the description! Try again :-)");
        }
        //If "todo" is entered with more words, use the information.
        String[] information = input.split(" ", 2);
        Todo newTD = new Todo(information[1]);
        dukeList.add(newTD);
        Data.writeToFile(newTD);
        System.out.println(UI.addedText + newTD.toString() + "\nNow you have " + ToDoList.numberOfTasks() + " tasks in the list");
    }
}
