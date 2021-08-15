public class DukeResponsePrettifier {
    private final String indent;
    private final String separator;

    public DukeResponsePrettifier(int indentationLevel, String separator) {
        this.indent = " ".repeat(indentationLevel);
        this.separator = separator;
    }

    private String indent(String str) {
        // Indent all lines
        String indentedString = str.replace("\n", "\n" + indent);
        return indent + indentedString;
    }

    private void printSeparator() {
        System.out.println(indent(separator));
    }

    public void print(String response) {
        printSeparator();
        System.out.println(indent(response));
        printSeparator();
    }
}
