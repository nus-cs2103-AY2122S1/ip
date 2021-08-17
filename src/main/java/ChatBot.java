import java.util.Scanner;

public class ChatBot {
    public ChatBot() {
        System.out.println(reply("Hello limpeh is Duke\nWhat u want?"));
    }

    private String reply(String content) {
        String bar = "________________________________";
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
}
