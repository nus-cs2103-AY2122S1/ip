package sariel.util.commands;


import sariel.util.controller.Sariel;

/**
 * When this command runs, the Sariel stops working.
 *
 */
public class ExitCommand implements Command {


    private static Sariel sariel;

    /**
     * The constructor of the ExitCommand.
     */
    public ExitCommand() {}

    /**
     * Sets the sariel for the program to exit.
     *
     * @param sariel
     */
    public static void setSariel(Sariel sariel) {
        ExitCommand.sariel = sariel;
    }


    @Override
    public String execute() {
        ExitCommand.sariel.quit();
        return "";
    }




}
