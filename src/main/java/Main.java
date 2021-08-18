import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Nature chatbot = new Nature();
        System.out.println(chatbot.greeting());
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                chatbot.printTaskList();
            } else {
                chatbot.addTask(s);
                System.out.println("added: " + s);
            }
        }
        sc.close();
        System.out.println(chatbot.farewell());
    }

}
