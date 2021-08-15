import java.util.Scanner;

public class Brain {

    public boolean decide(Speech speech, Storage storage) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        switch (input) {
            case "bye":
                speech.goodbye();
                return false;
            case "list":
                String[] temp = storage.getStorage();
                speech.speak(temp);
                return true;
            default :
                try {
                    if (input.startsWith("done")) {
                        String msg = storage.done(input.substring(4));
                        speech.done_msg(msg);
                    } else if (input.startsWith("deadline")) {
                        String msg = storage.deadline(input.substring(8));
                        speech.task_added(msg, storage.task_left());
                    } else if (input.startsWith("todo")) {
                        String msg = storage.todo(input.substring(4));
                        speech.task_added(msg, storage.task_left());
                    } else if (input.startsWith("event")) {
                        String msg = storage.event(input.substring(5));
                        speech.task_added(msg, storage.task_left());
                    } else {

                        input = storage.basicAdd(input);
                        speech.speak(input);
                    }

                } catch (Exception NumberFormatException) {
                    speech.error("an invalid code input");
                }
                return true;

        }
    }





}
