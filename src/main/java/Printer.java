/**
 * Encapsulates the Printer class that formats the String returned by ChatBot
 *
 * @author Clifford
 */

public class Printer {
    private static final String horizontalSeparator =
        "------------------------------------------------------------------------";

    public Printer() {}

    /**
     * prettyPrint formats the response of the ChatBot to present to the user.
     *
     * @param input the response by the ChatBot
     */
    public static void prettyPrint(String input) {
        StringBuilder sb = new StringBuilder();
        StringBuilder formattedSb = sb
            .append(horizontalSeparator)
            .append("\n")
            .append(input)
            .append("\n")
            .append(horizontalSeparator);
        System.out.println(formattedSb.toString());
    }
}
