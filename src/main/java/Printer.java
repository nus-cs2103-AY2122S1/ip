import java.util.ArrayList;

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

    public void PrintList(ArrayList<String> message) {
        for (int i = 1; i <= message.size(); i++) {
            String toPrint = String.format("%d. %s", i, message.get(i-1));
            System.out.println(toPrint);
        }
    }
}
