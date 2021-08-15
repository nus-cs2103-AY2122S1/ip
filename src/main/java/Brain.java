import java.nio.charset.StandardCharsets;
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
                if (input.startsWith("done")) {
                    try {
                        String msg = storage.done(input.substring(4));
                        speech.added(msg);
                    }catch (Exception NumberFormatException) {
                        speech.error("an invalid code input");
                    }
                    return true;
                } else {
                            input = storage.add(input);
                            speech.speak(input);
                            return true;
                        }
                }
    }




}
