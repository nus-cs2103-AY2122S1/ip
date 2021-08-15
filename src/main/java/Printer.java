public class Printer {
    private static final String horizontalSeparator =
        "------------------------------------------------------------------------";

    public Printer() {}

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
