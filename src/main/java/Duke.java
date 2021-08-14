public class Duke {

    public static void main(String[] args) {
        DukeCore dukeCore = new DukeCore();
        dukeCore.greet();
        String input = dukeCore.readLine();
        while (dukeCore.parseLine(input)) {  // while input is not "bye"
            input = dukeCore.readLine();
        }
        dukeCore.dismiss();
    }
}
