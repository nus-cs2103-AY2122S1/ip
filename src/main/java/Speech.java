import java.util.Random;

public class Speech {

    public Speech() {
    }

    public void welcome() {
        String[] duke_welcome = {"Hello! I'm Duke!" , "What can I do for you"};
        speak(duke_welcome);
    }

    public void added(String msg) {
        String[] duke_added = {"Nice! I've marked this task as done:", msg};
        speak(duke_added);
    }

    public void error(String error_msg) {
        String[] duke_error = {"Hmm, i dont quite understand you", "I think you made " + error_msg };
        speak(duke_error);
    }



    public void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }

    // Duke Speech Template
    public void speak(String[] in) {
        String top_border = "          ___________________________________________________\n";
        String bot_border = "          | ________________________________________________|\n" + "          |/\n";
        String emoticon = emoticon_rand();
        String msges = "";
        for( String x : in){
            msges += message_format(x, 48);
        }
        System.out.println( top_border + msges + bot_border + emoticon);
    }
    public void speak(String in) {
        String[] temp = {in};
        speak(temp);
    }
    private String message_format(String in, int longest) {
        String spaces = "          | ";
        String msg = spaces + String.format("%-"+longest+"s", in) +"|\n";
        return msg;
    }

    private String emoticon_rand() {
        String[] emoticon = {"(ﾍﾟ◇ﾟ)」" ,"( ＾◡＾)っ", "(*°▽°)ノ", "(o^ω^)" , "(✿˘▽˘)"};
        int rando = new Random().nextInt(emoticon.length-1);
        return emoticon[rando];
    }


}
