import java.util.Arrays;

/**
 * Utility class that handles all output to the console.
 */
public class Printer {
    /**
     * Parses and formats the output.
     *
     * @param output variable length arguments, where each argument corresponds to a new line to be printed
     * @return a formatted string
     */
    private static String outputParser(String ...output) {
        String divider = "\t************************************************************\n";
        return String.format(
                "%s%s%s",
                divider,
                Arrays.stream(output)
                        .map(str -> String.format("\t%s\n", str))
                        .reduce((res, curr) -> res + curr)
                        .orElse(""),
                divider);
    }

    /**
     * Prints the formatted output to the console.
     *
     * @param output variable length arguments, where each argument corresponds to a new line to be printed
     */
    public static void print(String ...output) {
        System.out.println(outputParser(output));
    }
}
