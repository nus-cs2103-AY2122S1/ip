import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {

        ChatBot bot = new ChatBot();
        System.out.println(bot.getStartMessage());

        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Boolean running = true;


        while (running) {

            String[] inputs = input.split(" ");
            // might want to change this to switch if if-else get too much
            if (inputs[0].equals("bye")) {
                System.out.println(bot.getExitMessage());
                running = false;
            } else if (inputs[0].equals("list")) {

                System.out.println(bot.getListMessage());
                input = sc.nextLine();

            } else if (inputs[0].equals("done")) {

                String message = bot.completeTask(Integer.parseInt(inputs[1]));
                System.out.println(message);
                input = sc.nextLine();

            } else {

                String message = bot.addItems(input);
                System.out.println(message);
                input = sc.nextLine();
            }
        }
        sc.close();


    }
}

// default modifier this class is only used in Duke.java
class ChatBot {

    static String line = "______________________________________\n";
    private ArrayList<Task> tasks = new ArrayList<>();

    public String getStartMessage() {
        return line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line;
    }

    public String getExitMessage() {
        return line + "bye! for now...\n" + line;
    }

    public String addItems(String input) {
        Task t = new Task(input);
        tasks.add(t);
        return line + "added: " + input +"\n" + line;
    }

    public String getListMessage() {
        String listMessage = "Here are your tasks... if you choose to do it...\n";

        for (int i = 0; i < tasks.size(); i++) {
            String status = tasks.get(i).getDone();
            String message = tasks.get(i).getMessage();
            listMessage = listMessage + (i + 1) + ".[" + status + "] " + message + "\n";
        }
        return line + listMessage + line;
    }

    public String completeTask(int index) {
        Task complete = tasks.get(index - 1);
        complete.completeTask();

        return line + "Well done! You finally completed it!\n" + line;
    }

}

