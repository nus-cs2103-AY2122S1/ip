public class DukeException extends Exception {
    private String error;
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";


    public DukeException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        System.out.println(div_line + "\n" + indent + "Oops! " + this.error + "\n" + div_line);
        return this.error;
    }
}
