/**
 *  This class represents the displayed logo throughout the program
 * @author Ryan Tian Jun
 */
public class Logo {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Logo() {

    }

    /**
     * Displays the logo Duke for the bot
     *
     * @return returns the bot logo
     */
    public String printLogo() {
        return logo;
    }

}
