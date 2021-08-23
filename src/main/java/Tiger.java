import tiger.app.Ui;

public class Tiger {
    public static void main(String[] args) {
        Ui ui = Ui.start();
        ui.runUntilStopped();
        ui.exit();
    }
}
