import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

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
        printOneLine(PrintType.GREETING_LINES.getPrintType());
    }

    // Prints the exit statement
    public static void printExitLine() {
        printOneLine(PrintType.BYE_LINE.getPrintType());
    }

    // Print list of task
    public static void printList() {
        String[] listLines = new String[taskList.size()+1];

        listLines[0] = PrintType.LIST_INTRO_LINE.getPrintType();

        // Add each item to listLines
        for (int i = 1; i <= taskList.size(); ++i) {
            listLines[i] = i + ". " + taskList.get(i-1).showTask();
        }

        printMultiLine(listLines);
    }

    // Print number of tasks left
    public static void printNumOfTask(){
        printOneLine("Now you have " + taskList.size() + " tasks in the list");
    }


    // Print acknowledgement of task added/ deleted depending on line
    public static void printAcknowledgement(String line, int index){
        String[] acknowledgeTaskLines = {
                line,
                "  " + taskList.get(index).showTask(),
        };
        printMultiLine(acknowledgeTaskLines);
    }

    // Adding a Todo to list of tasks
    public static void addTodo(String userInput) throws TodoException{

        if (userInput.equals("todo") || userInput.equals("")){
            throw new TodoException();
        }
        // Create new Todo instance an add it to end taskList
        taskList.add(new Todo (userInput));

        printAcknowledgement(RegexType.TASK_ADDED_LINE.getRegexType(), taskList.size()-1);
        printNumOfTask();
    }

    // Adding a Deadline to list of tasks
    public static void addDeadline(String userInput) throws DeadlineException {
        // Check if line follows the format "<description> /by <time/date>"
        if (!userInput.matches(RegexType.DEADLINE_REGEX.getRegexType())){
            throw new DeadlineException();
        }

        // get description and by from line
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String by = userInput.replaceAll(RegexType.GET_BY_REGEX.getRegexType(), "");

        // Create new Deadline instance an add it to end taskList
        taskList.add(new Deadline (description, by));

        printAcknowledgement(RegexType.TASK_ADDED_LINE.getRegexType(), taskList.size()-1);
        printNumOfTask();
    }

    // Adding an Event to list of tasks
    public static void addEvent(String userInput) throws EventException{
        // Check if userInput follows the format "<description> /at <time/date>"
        if (!userInput.matches(RegexType.EVENT_REGEX.getRegexType())){
            throw new EventException();
        }

        // get description and by from userInput
        String description = userInput.replaceAll(RegexType.GET_DESCRIPTION_REGEX.getRegexType(), "");
        String at = userInput.replaceAll(RegexType.GET_AT_REGEX.getRegexType(), "");

        // Create new Event instance an add it to end taskList
        taskList.add(new Event (description, at));

        printAcknowledgement(RegexType.TASK_ADDED_LINE.getRegexType(), taskList.size()-1);
        printNumOfTask();
    }

    public static void addNothing() throws NothingException{
        throw new NothingException();
    }

    // Delete line
    public static void deleteTask(String userInput)
            throws DeleteFormatException, DeleteRangeException {

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())){
            throw new DeleteFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= taskList.size()){
            throw new DeleteRangeException();
        }

        printAcknowledgement(PrintType.TASK_DELETED_LINE.getPrintType(), index);

        taskList.remove(index);

        printNumOfTask();
    }

    // Mark the task at the given index as done
    public static void markAsDone(String userInput)
            throws DoneFormatException, DoneAlreadyException, DoneRangeException{

        // Check if the command is done and is followed by a number
        // and if the index is within the range of number of tasks
        if (!userInput.matches(RegexType.DIGITS_REGEX.getRegexType())){
            throw new DoneFormatException();
        }

        int index = Integer.parseInt(userInput) - 1;
        if (index >= taskList.size()){
            throw new DoneRangeException();
        }
        // Check if task is already done
        if (taskList.get(index).isDone()){
            throw new DoneAlreadyException();
        }

        // Mark the index as done
        taskList.get(index).markAsDone();

        // Acknowledge task is done
        printAcknowledgement(PrintType.TASK_DONE_LINE.getPrintType(), index);
    }

    // Reads the input of the line to determine the command and run it
    public static void readInput(String userInput){

        // Get command from the userInput
        String[] splitLine = userInput.split(" ", 2);
        String command = splitLine[0];

        // Remove command from userInput
        userInput = userInput.replaceAll( RegexType.START_LINE_REGEX.getRegexType() + command + RegexType.SPACE_REGEX.getRegexType(), "");

        // Check the command type then execute the commands
        if(userInput.equals(InputType.LIST.getInputType())) {
            printList();

        } else if (command.equals(InputType.TODO.getInputType())){
            try {
                addTodo(userInput);
            } catch (TodoException e){
                printOneLine(PrintType.TODO_LINE.getPrintType());
            }

        } else if (command.equals(InputType.DEADLINE.getInputType())){
            try{
                addDeadline(userInput);
            } catch (DeadlineException e){
                printOneLine(PrintType.DEADLINE_LINE.getPrintType());
            }

        } else if (command.equals(InputType.EVENT.getInputType())){
            try{
                addEvent(userInput);
            } catch (EventException e){
                printOneLine(PrintType.EVENT_LINE.getPrintType());
            }

        } else if (command.equals(InputType.DELETE.getInputType()) ){
            try{
                deleteTask(userInput);
            } catch (DeleteFormatException e) {
                printOneLine(PrintType.DELETED_FORMAT_LINE.getPrintType());
            } catch (DeleteRangeException e){
                printOneLine(PrintType.OUT_OF_RANGE_LINE.getPrintType());
            }

        } else if (command.equals(InputType.DONE.getInputType()) ){
            try{
                markAsDone(userInput);
            } catch (DoneFormatException e) {
                printOneLine(PrintType.DONE_FORMAT_LINE.getPrintType());
            } catch (DoneRangeException e){
                printOneLine(PrintType.OUT_OF_RANGE_LINE.getPrintType());
            } catch (DoneAlreadyException e){
                printOneLine(PrintType.COMPLETED_LINE.getPrintType());
            }

        } else {
            try{
                addNothing();
            } catch (NothingException e){
                printOneLine(PrintType.NOT_RECOGNISED_LINE.getPrintType());
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
        while (!userInput.equals(InputType.BYE.getInputType())){

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