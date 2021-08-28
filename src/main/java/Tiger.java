import tiger.app.Ui;


public class Tiger {

    // TODO: implement flags for delete done combination, invalid todo combination
    // features: fallthrough commands
    // priority
    // delete done, invalid todo combination

    private Ui ui;

    public Tiger() {
        this.ui = new Ui();
    }

    public String start() {
        ui.start();
        return ui.getResponse();
    }

    public String getResponse(String input) {
        ui.iterateOnce(input);
        return ui.getResponse();
    }

    public boolean isExited() {
        return ui.isExited();
    }

}
