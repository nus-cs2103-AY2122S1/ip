import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.time.format.DateTimeFormatter;


public class BobbyBot {
    private final String DBPATH = "data/database.txt";
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BobbyBot()  {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage(DBPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
        }
    }


    /**
     * Performs command based on String user input
     * @param userInput string command for chatbot
     * @throws InvalidArgumentException Invalid or no arguments given
     * @throws TooManyArgumentsException Too many /by or /at connectors
     */
    public void doCommand(String userInput) throws InvalidArgumentException, TooManyArgumentsException {
        List<String> userInputList = new LinkedList<>(Arrays.asList(userInput.split(" ")));
        BotCommand command = BotCommand.valueOf(userInputList.get(0).toUpperCase());
        String description;
        String[] userInputArgs;

        switch (command) {
        case BYE:
            ui.sayBye();
            break;
        case LIST:
            ui.showLine();
            tasks.printList();
            ui.showLine();
            break;
        case DONE:
            ui.showLine();
            tasks.markAsDone(Integer.parseInt(userInputList.get(1)));
            ui.showLine();
            break;
        case DELETE:
            // check delete argument
            if (!isNumeric(userInputList.get(1))) {
                throw new InvalidArgumentException("Delete argument is not numeric");
            }
            ui.showLine();
            tasks.deleteTask(Integer.parseInt(userInputList.get(1)));
            ui.showLine();
            break;
        case TODO:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for todo");
            }
            description = String.join(" ", userInputList);
            ui.showLine();
            tasks.createToDo(description);
            ui.showLine();
            break;
        case DEADLINE:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for deadline");
            }
            //split description and by
            userInputArgs = String.join(" ", userInputList).split("/by ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given for deadline");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /by ");
            }
            description = userInputArgs[0];
            String by = userInputArgs[1];
            createDeadline(description, by);
            break;
        case EVENT:
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for event");
            }
            //split description and at
            userInputArgs = String.join(" ", userInputList).split("/at ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /at");
            }
            description = userInputArgs[0];
            String at = userInputArgs[1];
            ui.showLine();
            tasks.createEvent(description, at);
            ui.showLine();
            break;
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    /**
     * Creates a deadline task
     * @param description description of task
     * @param by date and time that the task should be completed by
     */
    private void createDeadline(String description, String by) {
        // convert string by to LocalDate
        try {
            LocalDateTime dateBy = LocalDateTime.parse(by, DT_FORMATTER);
            Task newDeadline = new Deadline(description, dateBy);
            tasks.add(newDeadline);
            ui.showLine();
            System.out.println("Got it. I've added this task:\n  " + newDeadline + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();
        } catch (DateTimeParseException e) {
            ui.showLine();
            System.out.println("Please input deadline date in the following format: [dd-mm-yyyy hh:mm]");
            ui.showLine();
        }
    }

    /**
     * Helper function to check if string is numeric
     * @param str string to test if numeric
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
