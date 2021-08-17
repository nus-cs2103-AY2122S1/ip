public class Printer {
    String border;
    public Printer(String border) {
        this.border = border;
    }

    public void PrintMessage(String message) {
        System.out.println(border);
        System.out.println(message);
        System.out.println(border);
    }
}
