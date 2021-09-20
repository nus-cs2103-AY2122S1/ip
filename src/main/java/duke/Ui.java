package duke;

public class Ui {
    private static final String LINEBREAK = "____________________________________________________________";

    public void print(String s) {
        System.out.printf("%s\n%s\n%s\n", LINEBREAK, s, LINEBREAK);
    }

    /**
     * Prints greeting message for the Duke program.
     */
    public void greet() {
        System.out.println(
                " BOW BEFORE ME, FOR I AM SQUIRTLE, DESTROYER OF MEN, TAKER OF LIVES.\n THE GODS FEARED MY EXISTENCE,"
                        + " SO THEY BANISHED ME TO YOUR MORTAL REALM TO SAVE YOUR MISERABLE LIFE.");
        System.out.println(LINEBREAK);
        System.out.print("SO WHAT DO YOU WANT DO, INSECT?\n ");
    }
}
