import java.util.ArrayList;

public class Task {
    private ArrayList<String> list = new ArrayList<>();
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    public void add(String item) {
        this.list.add(item);
    }

    public void printAll() {
        System.out.println(div_line);
        for (int i = 1; i < this.list.size() + 1; i++) {
            System.out.println(indent + i + " " + list.get(i-1));
        }
        System.out.println(div_line + "\n");
    }
}
