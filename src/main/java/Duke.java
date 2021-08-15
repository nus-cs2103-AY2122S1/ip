public class Duke {
    public void run() {
        this.greetUser();
    }

    public void printMessage(String message) {
        String formatDisplay = String.format("    %s", message);
        System.out.println(formatDisplay);
    }

    public void greetUser() {
        String greetMessage = "Hello! I'm Saitama";
        String detailsMessage = "I do 100 sit-ups, 100 push-ups, 100 squats and a 10 kilometer run every day! No cap";
        printMessage(greetMessage);
        printMessage(detailsMessage);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
