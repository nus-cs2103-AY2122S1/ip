import java.util.ArrayList;
import java.util.Scanner;

public class Pib {
    public static String DIVIDER = "____________________________________________________________\n";
    private ArrayList<Task> list;
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
                endPib();
                break;
            } else if (next.equalsIgnoreCase("list")) {
                displayList();
            } else if (next.startsWith("done ")) {
                if (list.size() > 0) {
                    markAsDone(next.substring(5));
                } else {
                    System.out.println(DIVIDER + "Add an item first!\n" + DIVIDER);
                }
            } else {
                list.add(new Task(next));
            }
        }
    }

    private void endPib() {
        System.out.println(DIVIDER + "Bye! See you next time!\n" + DIVIDER);
        sc.close();
    }

    private void displayList() {
        if (list.size() == 0) {
            System.out.println(DIVIDER + "Nothing added yet\n" + DIVIDER);
            return;
        }
        System.out.println(DIVIDER);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + list.get(i).displayTask())  ;
        }
        System.out.println(DIVIDER);
    }

    private void markAsDone(String s) {
        if (s.isBlank()) {
            System.out.println(DIVIDER + "Tell me which item to mark as complete!\n " + DIVIDER);
        } else {
            list.get(Integer.parseInt(s) - 1).markAsDone();
        }
    }

    public static void main(String[] args) {
        Pib p = new Pib();
    }
}
