import java.util.Scanner;

public class Main {

    /**
     * a method that checks if the input is in the format "done"  followed by a number
     * this is useful to determine whether the user is marking a task as done
     *
     * @param s the input string
     * @return return 0 if the input is not in the required format (which means the user is not intending to mark
     * a task as done; otherwise, return the index of the task to be marked as done
     */
    public static int checkInput(String s) {
        if (s.length() < 6) return 0;
        if (!s.substring(0, 4).equals("done")) return 0;
        try {
            int index  = Integer.parseInt(s.substring(5));
            return index;
        } catch (NumberFormatException e){
            return 0;
        }
    }

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
                int index = checkInput(s);
                if (index > 0) {
                    chatbot.markTaskDone(index);
                } else {
                    chatbot.addTask(s);
                    System.out.println("added: " + s);
                }
            }
        }
        sc.close();
        System.out.println(chatbot.farewell());
    }

}
