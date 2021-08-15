public class DukeResponsePrettifier {
    private final int indentationLevel;
    private final String separator;

    public DukeResponsePrettifier(int indentationLevel, String separator) {
        this.indentationLevel = indentationLevel;
        this.separator = separator;
    }

    private String indent(String str) {
        return " ".repeat(indentationLevel) + str;
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
