import java.util.ArrayList;
import java.util.Scanner;

public class Pib {
    private static String DIVIDER = "____________________________________________________________\n";
    private ArrayList<String> list;
    private Scanner sc;

    public Pib() {
        System.out.println(DIVIDER + "Hello! I'm Pib\n" + "Tell me something!\n" + DIVIDER);
        list = new ArrayList<>();
        sc = new Scanner(System.in);
        readInput();
    }

    private void readInput() {
        while (sc.hasNextLine()) {
            String next = sc.nextLine();

            if (next.equalsIgnoreCase("BYE")) {
                System.out.println(DIVIDER + "Bye! See you next time!\n" + DIVIDER);
                sc.close();
                break;
            } else if (next.equalsIgnoreCase("list")) {
                displayList();
            } else {
                addToList(next);
            }
        }
    }

    private void addToList(String item) {
        list.add(item);
        System.out.println(DIVIDER + "added: " + item + "\n" + DIVIDER);
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println(DIVIDER + "Nothing added yet\n" + DIVIDER);
            return;
        }
        System.out.println(DIVIDER);
        for (String item : list) {
            String output = (list.indexOf(item) + 1) + ". " + item + "\n";
            System.out.println(output);
        }
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        Pib p = new Pib();
    }
}
