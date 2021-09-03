package duke;

import java.util.function.Function;

/**
 * Class to handle User Interface interactions.
 */
public class UI {

    private static Function<String, String> DEFAULT_PREPROCESSOR = (String source) -> {
        String border = "--------------------------------------------------";
        StringBuilder out = new StringBuilder();
        for (String line : source.split("\n")) {
            out.append("\t" + line + "\n");
        }
        return border + "\n"
                + out.toString()
                + border;
    };

    private Function<String, String> preprocessor;
    /**
     * Constructor for  UI with a custom preprocessor.
     *
     * @param preprocessor Function\<String,String\> to transform the input strings for display.
     */
    UI(Function<String, String> preprocessor) {
        this.preprocessor = preprocessor;
    }

    /**
     * Constructor for UI using default preprocessor.
     */
    UI() {
        this(UI.DEFAULT_PREPROCESSOR);
    }

    /**
     * Displays the ascii startup logo.
     */
    public void displayLogo() {
        String logo = "  _____          _   _    ___   ___   ___   ___  \n"
                + " |  __ \\   /\\   | \\ | |  / _ \\ / _ \\ / _ \\ / _ \\ \n"
                + " | |  | | /  \\  |  \\| | | (_) | | | | | | | | | |\n"
                + " | |  | |/ /\\ \\ | . ` |  \\__, | | | | | | | | | |\n"
                + " | |__| / ____ \\| |\\  |    / /| |_| | |_| | |_| |\n"
                + " |_____/_/    \\_\\_| \\_|   /_/  \\___/ \\___/ \\___/ \n";
        System.out.println(logo);
    }

    /**
     * Displays the welcome message.
     */
    public void displayWelcome() {
        this.display("Hello, I'm DAN 9000\n"
                + "How can I help you?");
    }

    /**
     * Displays the provided string using preprocessor.
     *
     * @param content message String.
     */
    public void display(String content) {
        display(content, this.preprocessor);
    }

    /**
     * Displays the provided String using a provided processor.
     *
     * @param content message String.
     * @param preprocessor Function\<String,String\> to transform the input strings for display.
     */
    public void display(String content, Function<String, String> preprocessor) {
        System.out.println(this.preprocessor.apply(content));
    }

    /**
     * Displays the error associated with loading save file.
     */
    public void showLoadingError() {
        String savePath = "./data/duke.txt";
        this.display("ERROR: unable to load save file from " + savePath);
    }

    /**
     * Displays a general exception encountered.
     *
     * @param e Exception to display the message of.
     */
    public void displayException(Exception e) {
        this.display(e.getMessage());
    }
}
