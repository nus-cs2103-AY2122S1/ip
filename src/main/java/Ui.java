public class Ui {
    public void greeting() {
        // Introduction message
        String logo = "______ _____ _   _ \n" +
                "| ___ \\  ___| \\ | |\n" +
                "| |_/ / |__ |  \\| |\n" +
                "| ___ \\  __||     |\n" +
                "| |_/ / |___| |\\  |\n" +
                "\\____/\\____/\\_| \\_/\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();
    }

    /**
     * Prints dukes reply in between two lines.
     * @param message Duke's reply.
     */
    public void dukeReply(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Draws a line on the screen to separate speech.
     */
    public void drawLine() {
        System.out.println("------------------------");
    }
}
