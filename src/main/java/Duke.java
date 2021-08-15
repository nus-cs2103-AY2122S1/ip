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
                for (int i = 0; i < index; i++) {
                    Task curr = myList[i];
                    System.out.printf("%s.[%s] %s\n", i + 1, curr.getStatusIcon(), curr.description);
                }
            } else if (text.contains("done")){
                int index = Integer.parseInt(text.split(" ")[1]) - 1;
                Task curr = myList[index];
                curr.setDone();
                System.out.printf("[%s] %s\n", curr.getStatusIcon(), curr.description);
            } else {
                myList[index] = new Task(text);
                index++;
                System.out.println("added: " + text);
            }
        }
    }
}
