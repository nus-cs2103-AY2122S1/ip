public class DukeException extends Exception{
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";
    private String message;

    DukeException(String m) {
        this.message = m;
    }

    @Override
    public String toString() {
        return div + "\n" + ind2 + message + "\n" + div;
    }
}
