import java.util.Scanner;

public class Duke {
    static private Task[] myList = new Task[100];
    static private int index = 0;

    public static void main(String[] args) {
        System.out.println("hi sis, type out your task right away! :D");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();
            if (text.equals("q")) {
                System.out.println("ciao!");
                break;
            } else if (text.equals("ls")) {
                printList(index);
            } else if (text.contains("done")){
                markAsDone(text);
            } else {
                addTask(text, index);
            }
        }
    }

    private static void addTask(String text, int i) {
        if (text.contains("todo")) {
            myList[i] = new Todo(text.split(" ", 2)[1]);
        } else if (text.contains("deadline")) {
            String[] task = text.split(" ", 2)[1].split(" /by ", 2);
            myList[i] = new Deadline(task[0], task[1]);
        } else if (text.contains("event")) {
            String[] task = text.split(" ", 2)[1].split(" /at ", 2);
            myList[i] = new Event(task[0], task[1]);
        } else {
            System.out.println("Please use the keyword --todo, deadline or event--");
            return;
        }
        System.out.println("added: " + myList[i]);
        index++;
        System.out.printf("Now you have %d task(s) in the list.\n", index);
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
