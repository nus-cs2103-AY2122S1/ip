package duke;

import java.util.function.Function;

/**
 * Class to handle User Interface interactions
 */
public class UI {

    Function<String, String> preprocessor;

    /**
     * Construct duke.UI with a custom preprocessor
     * @param preprocessor
     */
    UI(Function<String, String> preprocessor) {
        this.preprocessor = preprocessor;
    }

    /**
     * Construct duke.UI using default preprocessor
     */
    UI(){
        this(
            (String source) -> {
                String border = "--------------------------------------------------";
                StringBuilder out = new StringBuilder();
                for (String line:source.split("\n")){
                    out.append("\t" + line + "\n");
                }
                return border + "\n"
                    + out.toString()
                    + border;
            });
    }

    public void displayLogo(){
        String logo =
            "  _____          _   _    ___   ___   ___   ___  \n" +
            " |  __ \\   /\\   | \\ | |  / _ \\ / _ \\ / _ \\ / _ \\ \n" +
            " | |  | | /  \\  |  \\| | | (_) | | | | | | | | | |\n" +
            " | |  | |/ /\\ \\ | . ` |  \\__, | | | | | | | | | |\n" +
            " | |__| / ____ \\| |\\  |    / /| |_| | |_| | |_| |\n" +
            " |_____/_/    \\_\\_| \\_|   /_/  \\___/ \\___/ \\___/ \n";
        System.out.println(logo);
    }

    public void displayWelcome() {
        this.display("Hello, I'm DAN 9000\n"
            + "How can I help you?");
    }

    /**
     * Display the provided string using preprocessor
     *
     * @param content
     */
    public void display(String content) {
        display(content, this.preprocessor);
    }

    /**
     * Display the provided String using a provided processor
     *
     * @param content
     * @param preprocessor
     */
    public void display(String content, Function<String, String> preprocessor) {
        System.out.println(this.preprocessor.apply(content));
    }

    /**
     * Display the error associated with loading save file
     */
    public void showLoadingError(){
        String savePath = "./data/duke.txt";
        this.display("ERROR: unable to load save file from "+ savePath);
    }

    public void displayException(Exception e){
        this.display(e.getMessage());
    }
}
