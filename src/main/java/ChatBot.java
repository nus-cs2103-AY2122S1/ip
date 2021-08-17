import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot implements Addable {
    private final ArrayList<String> list;
    final String bar = "________________________________";

    public ChatBot() {
        System.out.println(reply("Hello i is Duke\nWhat u want?"));
        list = new ArrayList<>();
    }

    private String reply(String content) {
        return bar + "\n" + content + "\n" + bar;
    }

    private void exit() {
        System.out.println(reply("i zao first"));
    }

    public void echo() {
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("bye")) {
            exit();
        } else {
            System.out.println(reply(input));
            echo();
        }
    }

    public void add() {
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("list")) {
            displayList();
        } else if (input.equals("bye")) {
            exit();
        } else {
            list.add(input);
            System.out.println(reply("added: " + input));
            add();
        }
    }

    public void displayList() {
        System.out.println(bar);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(bar);
        add();
    }
}
