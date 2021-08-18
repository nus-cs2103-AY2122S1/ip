import java.util.Locale;

public class Logic {

    /**
     * The process is the main function to process the parsed string from presentation.
     * @param command refers to the parsed string. See presentation for parsing
     * @throws InvalidCommandException is throwed when there is an invalid command in the form of a string
     */
    public static void process(String command) throws InvalidCommandException{
        System.out.println("Process logic called");

        //Logic to check each individual commands, checks for special commands first, then checks for other input
        if (command.equals("")) {
            throw new EmptyCommandException();
        } else {
            String[] listOfCommandInputs = command.split(" ");
            String actionCommand = listOfCommandInputs[0].toLowerCase();
            System.out.println("Passed on to persistence stage");
            if (actionCommand.equals("list")) {
                Persistence.printLog();
            } else if (actionCommand.equals("done")) {
                int pos = Integer.parseInt(listOfCommandInputs[1]);
                if (pos > Task.getNumberOfTask()) {
                    throw new InvalidCommandException();
                }
                Task currentTask = Persistence.getTask(pos - 1 );
                currentTask.completeTask();
                System.out.println("Ohhhh myyyy. I have been waiting for this quest to complete for ages.");
            } else {
                Persistence.addToLog(new Task(command));
            }
        }
    }
}
