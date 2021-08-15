import java.util.Scanner;

public class Duke {
    static private Task[] myList = new Task[100];
    static private int index = 0;

    public static void main(String[] args) {
        System.out.println("hi sis, type out your task right away! :D");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            if (text.equals("bye")) {
                System.out.println("ciao!");
                break;
            } else if (text.equals("list")) {
                printList(index);
            } else if (text.contains("done")){
                markAsDone(text);
            } else {
                addTask(text, index);
                index++;
            }
        }
    }

    private static void addTask(String text, int i) {
        myList[i] = new Task(text);
        System.out.println("added: " + text);
    }

    private static void printList(int len) {
        for (int i = 0; i < len; i++) {
            Task curr = myList[i];
            System.out.printf("%s. %s\n", i + 1, curr);
        }
    }

    private static void markAsDone(String text) {
        int index = Integer.parseInt(text.split(" ")[1]) - 1;
        Task curr = myList[index];
        curr.setDone();
        System.out.println(curr);
    }
}
