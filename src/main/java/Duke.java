import java.util.Scanner;
import java.util.Random;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String greetingMessage = "Henlo, this is Duke.\nHow may I help?";
        System.out.println(greetingMessage);
        String byeString = "bye";
        Random rand = new Random();
        int impatienceCount = 0;

        String[] impatientEchoSmall = new String[] {
                "%s?? What does that even mean???\n",
                "Umm.. What do you want me to say to '%s'?\n",
                "...? %s and...?\n",
                "Sorry, but what does '%s' mean?\n"
        };

        String[] impatientEchoBig = new String[] {
                "You really want me to repeat what you said AGAIN?\n%s\nHAPPY NOW?\n",
                "When will this stop.... %s.........\n",
                "I'm sorry please leave me alone.... %s right..?\n",
                "OKAY %s OKAY I GET IT PLEASE STOP :(\n"
        };

        String nonimpatientBye = "Bye~! Teehee~~";
        String impatientByeSmall = "Bye...";
        String impatientByeBig = "Goodbye and Good Riddance!!!!!!";


        while (true) {
            String response = sc.next();
            if (response.equals(byeString)) {
                if (impatienceCount < 3) {
                    System.out.println(nonimpatientBye);
                    break;
                } else if (impatienceCount < 6) {
                    System.out.println(impatientByeSmall);
                    break;
                } else {
                    System.out.println(impatientByeBig);
                    break;
                }

            } else {
                impatienceCount += 1;
                if (impatienceCount < 3) {
                    System.out.println(response);
                } else if (impatienceCount < 6) {
                    int strPos = rand.nextInt(impatientEchoSmall.length - 1);
                    System.out.printf(impatientEchoSmall[strPos], response);
                } else {
                    int strPos = rand.nextInt(impatientEchoBig.length - 1);
                    System.out.printf(impatientEchoBig[strPos], response);
                }
            }
        }

    }
}
