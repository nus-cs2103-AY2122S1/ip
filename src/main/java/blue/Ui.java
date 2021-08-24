package blue;

class Ui {
    private static final String LOADING_ERROR_CONTENT
            = "☹ OOPS!!! I'm sorry, but I can't find your previous tasks :-(";
    private static final String LOGO = " ____  _                \n"
            + "|  . \\| | _   _   ____  \n"
            + "|____/| || | | | /  _  \\\n"
            + "|  . \\| || |_| ||   ___/\n"
            + "|____/|_||_____| \\_____/\n";
    private static final String GREET_CONTENT = "Hello! I'm Blue\nWhat can I do for you?";
    private static final String EXIT_CONTENT = "Bye. Hope to never see you again!";

    void showLoadingError() {
        speak(LOADING_ERROR_CONTENT);
    }

    void showLogo() {
        System.out.println(LOGO);
    }

    void greet() {
        speak(GREET_CONTENT);
    }

    void confused() {
        speak("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    void goodbye() {
        speak(EXIT_CONTENT);
    }

    void speak(String content) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println(content);
        System.out.println(line);
    }
}
