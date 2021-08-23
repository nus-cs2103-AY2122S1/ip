import tiger.app.Ui;

public class Tiger {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.start();
        ui.runUntilStopped();
        ui.exit();
    }
}
