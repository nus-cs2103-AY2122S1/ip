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
        } else if (command.equals("list")) {
            System.out.println("Passed on to persistence stage");
            Persistence.print_log();
        } else {
            System.out.println(command.getClass());
            System.out.println("Passed on to persistent layer");
            Persistence.addToLog(command);
        }
    }
}
