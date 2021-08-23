import tiger.app.Ui;

public class Tiger {
    public static void main(String[] args) {

        // TODO: remove case sensitivity in commands and search
        // TODO: implement flags for delete done combination, invalid todo combination
        // TODO: force users to have first letter upper case
        // TODO: prettify list so that lists with > 10 items will not destroy spacing

        Ui ui = new Ui();
        ui.start();
        ui.runUntilStopped();
        ui.exit();
    }
}
