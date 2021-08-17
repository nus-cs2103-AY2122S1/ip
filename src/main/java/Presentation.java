public class Presentation {

    protected Presentation() {

    }

    protected void horizontalLine() {
        for (int i = 0; i < 30; i++) {
            System.out.print("-");
        }
    }

    protected void space() {
        System.out.println();
    }

    protected void response(String input) {
        horizontalLine();
        space();
        System.out.println(">> " + input);
        horizontalLine();
        space();
        enterCommand();
    }

    protected void enterCommand() {
        System.out.print("Enter command: ");
    }
}
