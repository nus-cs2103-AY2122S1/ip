
import java.util.Random;
import java.util.Scanner;


public class Duke {

    // Duke Response Template
    private static String duke_speak(String[] in) {
        String top_border = "          ___________________________________________________\n";
        String bot_border = "          | ________________________________________________|\n" + "          |/\n";
        String emoticon = emoticon_rand();
        String msges = "";
        for( String x : in){
            msges += message_format(x, 48);
        }
        return top_border + msges + bot_border+ emoticon;
    }
    private static String duke_speak(String in) {
        String[] temp = {in};
        return duke_speak(temp);
    }
    private static String message_format(String in, int longest) {
        String spaces = "          | ";
        String msg = spaces + String.format("%-"+longest+"s", in) +"|\n";
        return msg;
    }

    private static String emoticon_rand() {
        String[] emoticon = {"(ﾍﾟ◇ﾟ)」" ,"( ＾◡＾)っ", "(*°▽°)ノ", "(o^ω^)" , "(✿˘▽˘)"};
        int rando = new Random().nextInt(emoticon.length-1);
        return emoticon[rando];
    }

    private static String welcome() {
        String[] duke_welcome = {"Hello! I'm Duke!" , "What can I do for you"};
        return duke_speak(duke_welcome);
    }


    public static void main(String[] args) {
        System.out.println(welcome());
        Scanner sc = new Scanner(System.in);

        String user_input = "";

        while (true) {
            System.out.print("Say something to Duke: ");
            user_input = sc.next();
            if (user_input.equals("bye")) {
                break;
            }
            System.out.println(duke_speak(user_input));
        }
        System.out.println( duke_speak("Bye. Hope to see you again soon!" ));
    }
}
