package ui;

/**
 * Ui class that format all output from Dub chatbot
 */
public class Ui {
    /**
     * Returns neatly formatted response to the user.
     *
     * @param line response from Dub chatbot before formatted.
     */
    public static void print(String line) {
        System.out.print("\t" + line.replace("\n", "\n\t")
                + "\n\t____________________________________________________________\n\n");
    }
}
