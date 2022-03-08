package jaden;

import middleware.Middleman;
import parser.ParsedInput;
import ui.JadenGui;

public class Duke {

    private final static String GREETING = "Hello! I'm Jaden\nHow Are You? It's Been A While Since We Last Spoke.";
    private final JadenGui ui;
    private final Middleman middleman;
    private String lastResponse;


    public Duke(Middleman middleman, JadenGui ui) {
        this.ui = ui;
        this.middleman = middleman;

        greet();
    }

    public void greet() {
        ui.displayString(GREETING);
    }

    public String processResponse(String res) {
        assert res != null;
        ParsedInput parsedInput = new ParsedInput(res);
        String output = middleman.processParsedInput(parsedInput);
        return output;
    }

    public String getResponse() {
        return lastResponse;
    }
}
