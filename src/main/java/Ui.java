public class Ui {
    private static String line = "*･゜ﾟ･*･゜ﾟ･*:.｡..｡.:*･'(*ﾟ▽ﾟ*)'･*:.｡. .｡.:*･゜ﾟ･*゜ﾟ･*";

    public static String format(String msg) {
        return msg + "\n\n" + line;
    }

    public void displayWelcomeMsg() {
        System.out.println(format("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public void displayGoodbyeMsg() {
        System.out.println(format("Hello! I'm Duke\nWhat can I do for you?"));
    }

    public void displayErrorMsg(DukeException e) {
        System.out.println(format(e + "\n\nWhat else can I do for you?"));
    }
}
