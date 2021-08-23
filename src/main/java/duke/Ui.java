package duke;

/**
 * Deals with interactions with the user
 */
public class Ui {
    /**
     * Prints greeting message to user from Jarvis and the question prompting for the first user
     * input
     */
    public Ui() {
        String logo = "\n" +
                "       __       ___      .______     ____    ____  __       _______.\n" +
                "      |  |     /   \\     |   _  \\    \\   \\  /   / |  |     /       |\n" +
                "      |  |    /  ^  \\    |  |_)  |    \\   \\/   /  |  |    |   (----`\n" +
                ".--.  |  |   /  /_\\  \\   |      /      \\      /   |  |     \\   \\    \n" +
                "|  `--'  |  /  _____  \\  |  |\\  \\----.  \\    /    |  | .----)   |   \n" +
                " \\______/  /__/     \\__\\ | _| `._____|   \\__/     |__| |_______/    \n" +
                "                                                                    \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("----------------------------------");
        System.out.println("Hi! I am Jarvis, your personal assistant :)\n");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");
    }
}
