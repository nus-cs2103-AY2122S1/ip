import tiger.app.Ui;

public class Tiger {
    public static void main(String[] args) {

        // TODO: implement flags for delete done combination, invalid todo combination
        // TODO: implement priority

        // features: fallthrough commands
        // priority
        // delete done, invalid todo combination
        System.out.println("This is a test from gradle.");

        Ui ui = new Ui();
        ui.start();
        ui.runUntilStopped();
        ui.exit();
    }
}
