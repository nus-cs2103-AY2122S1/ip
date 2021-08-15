import java.util.Random;

public class Speech {

    boolean basicMode;

    public Speech(boolean mode) {
        this.basicMode = mode;
    }


    public void welcome() {
        String[] dukeWelcome = {"Hello! I'm Duke!" , "What can I do for you"};
        speak(dukeWelcome);
    }

    public void done_msg(String msg) {
        String[] dukeAdded = {"Nice! I've marked this task as done:", msg};
        speak(dukeAdded);
    }

    public void task_added(String msg, int task_left) {
        String[] dukeAddedTask = {"Got it. I've added this task:", "  " + msg, "Now you have " + task_left + " tasks in the list."};
        speak(dukeAddedTask);
    }

    public void error(String error_msg) {
        String[] dukeError = {"Hmm, i dont quite understand you", "I think you made " + error_msg };
        speak(dukeError);
    }

    public void goodbye() {
        speak("Bye. Hope to see you again soon!");
    }




    // Duke Speech Template
    public void speak(String[] in) {
        if (!basicMode){
            String topBorder = "          ___________________________________________________\n";
            String botBorder = "          | ________________________________________________|\n" + "          |/\n";
            String emoticon = emoticonRand();
            String msges = "";
            for( String x : in){
                msges += message_format(x, 48);
            }
            System.out.println( topBorder + msges + botBorder + emoticon);
        } else {
            for( String x : in){
                System.out.println(x);
            }
        }
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

    private String emoticonRand() {
        String[] emoticon = {"(ﾍﾟ◇ﾟ)」" ,"( ＾◡＾)っ", "(*°▽°)ノ", "(o^ω^)" , "(✿˘▽˘)"};
        int rando = new Random().nextInt(emoticon.length-1);
        return emoticon[rando];
    }


}
