import java.util.Scanner;

public class Duke {

    // Lines to be printed
    public static final String[] GREETING_LINES = {
            "Hello! I'm Duke",
            "What can I do for you?"};
    public static final String BYE_LINE =
            "Bye. Hope to see you again soon!";
    public static final String TASK_DONE_LINE =
            "Nice! I've marked this task as done:\n  ";
    public static final String LIST_INTRO_LINE =
            "Here are the tasks in your list";
    public static final String NOT_RECOGNISED_LINE =
            ":( Oh no! Command not recognised";
    public static final String DONE_FORMAT_LINE =
            ":( Oh no! Done must follow the format: done <index>";
    public static final String OUT_OF_RANGE_LINE =
            ":( Oh no! The index given is out of the range of the number of tasks.";
    public static final String COMPLETED_LINE =
            ":( Oh no! The task has already been completed.";
    public static final String TODO_LINE =
            ":( Oh no! Description of todo cannot be empty";
    public static final String DEADLINE_LINE =
            ":( Oh no! Deadline must follow the format <description> /by <time/date> ";
    public static final String EVENT_LINE =
            ":( Oh no! Event must follow the format <description> /at <time/date> ";

    // Commands Constant
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String DONE = "done";

    // Regex Constants
    public static final String GET_DESCRIPTION_REGEX = "/.+";
    public static final String GET_AT_REGEX = ".+/at ";
    public static final String GET_BY_REGEX = ".+/by ";
    public static final String SPACE_REGEX = "\\s";
    public static final String START_LINE_REGEX = "^";
    public static final String DIGITS_REGEX = "\\d+";
    public static final String DEADLINE_REGEX = ".+/by.+";
    public static final String EVENT_REGEX = ".+/at.+";

    // Task variables
    public static Task[] taskList= new Task[100];
    public static int taskCount = 0;

    // Prints a horizontal dash line
    public static void printDashLine(){
        System.out.println("--------------------------------"
                + "---------------------------------------");
    }

    public static void printOneLine(String line){
        printDashLine();
        System.out.println(line);
        printDashLine();
    }

    public static void printMultiLine(String[] lines){
        printDashLine();
        for (String line : lines){
            System.out.println(line);
        }
        printDashLine();
    }

    // Prints the prompt before every user input
    public static void printPrompt(){
        System.out.print("  > ");
    }

    // Prints the greet statement
    public static void printGreeting(){
        printMultiLine(GREETING_LINES);
    }

    // Prints the exit statement
    public static void printExitLine() {
        printOneLine(BYE_LINE);
    }

    // Print acknowledgement of input
    public static void printAcknowledgement(){
        String[] acknowledgeTaskLines = {
                "Got it. I've added this task:\n  ",
                taskList[taskCount-1].showTask(),
                "Now you have " + taskCount + " tasks in the list"
        };
        printMultiLine(acknowledgeTaskLines);
    }

    // Adding a Todo to list of tasks
    public static void addTodo(String userInput) throws TodoException{

        if (userInput.equals("todo") || userInput.equals("")){
            throw new TodoException();
        }
        // Create new Todo instance an add it to end taskList
        taskList[taskCount] = new Todo (userInput);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    // Adding a Deadline to list of tasks
    public static void addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(DEADLINE_REGEX)){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String by = userInput.replaceAll(GET_BY_REGEX, "");

        // Create new Deadline instance an add it to end taskList
        taskList[taskCount] = new Deadline (description, by);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    // Adding an Event to list of tasks
    public static void addEvent(String userInput) throws EventException{
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(EVENT_REGEX)){
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(GET_DESCRIPTION_REGEX, "");
        String at = userInput.replaceAll(GET_AT_REGEX, "");

        // Create new Event instance an add it to end taskList
        taskList[taskCount] = new Event (description, at);
        taskCount = taskCount + 1;

        printAcknowledgement();
    }

    public static void addNothing() throws NothingException{
        throw new NothingException();
    }

    // Print list of task
    public static void printList() {
        String[] listLines = new String[taskCount+1];

        listLines[0] = LIST_INTRO_LINE;

        // Add each item to listLines
        for (int i = 1; i <= taskCount; ++i) {
            listLines[i] = i + ". " + taskList[i-1].showTask();
        }

        printMultiLine(listLines);
    }

    // Mark the task at the given index as done
    public static void markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(DIGITS_REGEX)){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= taskCount){
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (taskList[index].isDone()){
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        taskList[index].markAsDone();

        // Acknowledge task is done
        printMultiLine(new String[]{
                TASK_DONE_LINE,
                taskList[index].showTask()
        });
    }

    // Reads the input of the line to determine the command and run it
    public static void readInput(String userInput){

        // Get command from the userInput
        String[] splitLine = userInput.split(" ", 2);
        String command = splitLine[0];

        // Remove command from userInput
        userInput = userInput.replaceAll(START_LINE_REGEX + command + SPACE_REGEX, "");

        // Check the command type then execute the commands
        if(userInput.equals(LIST)) {
            printList();

        } else if (command.equals(DONE) ){
            try{
                markAsDone(userInput);
            } catch (DoneFormatException e) {
                printOneLine(DONE_FORMAT_LINE);
            } catch (DoneRangeException e){
                printOneLine(OUT_OF_RANGE_LINE);
            } catch (DoneAlreadyException e){
                printOneLine(COMPLETED_LINE);
            }

        } else if (command.equals(TODO)){
            try {
                addTodo(userInput);
            } catch (TodoException e){
                printOneLine(TODO_LINE);
            }

        } else if (command.equals(DEADLINE)){
            try{
                addDeadline(userInput);
            } catch (DeadlineException e){
                printOneLine(DEADLINE_LINE);
            }

        } else if (command.equals(EVENT)){
            try{
                addEvent(userInput);
            } catch (EventException e){
                printOneLine(EVENT_LINE);
            }
        } else {
            try{
                addNothing();
            } catch (NothingException e){
                printOneLine(NOT_RECOGNISED_LINE);
            }
        }
    }

    // Continuously take in input and running the commands until input is BYE then exit
    public static void runDuke(){
        // Initialise variables to get input from user
        String userInput;
        Scanner in = new Scanner(System.in);

        // Get the first input from user
        printPrompt();
        userInput = in.nextLine();

        // While the input is not "bye", add input to array of tasks and get another input
        while (!userInput.equals(BYE)){

            readInput(userInput);

            // Get another input
            printPrompt();
            userInput = in.nextLine();
        }
    }

    public static void main(String[] args) {
        // Greets user
        printGreeting();

        // Run Duke until it exits
        runDuke();

        // When the input is BYE and we exit from the loop, print exit line
        printExitLine();
    }
}