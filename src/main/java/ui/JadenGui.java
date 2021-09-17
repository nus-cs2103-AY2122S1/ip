package ui;

import java.util.List;

import ui.view.MainWindowController;

public class JadenGui {

    private final MainWindowController[] mainWindow;

    public JadenGui(MainWindowController... mainWindow) {
        this.mainWindow = mainWindow;
    }

    private final static String SEPARATOR = "_____________________________________________________________________";

    public <T> String getListAsString(List<T> list) {
        String outString = "";
        for (int i = 0; i < list.size(); i++) {
            outString += (i+1) + ". " + list.get(i).toString() + "\n";
        }

        if(!outString.isEmpty()) {
            outString = outString.substring(0, outString.length() - 1);
        }

         return outString;
    }

    public void displayString(String text) {
        mainWindow[0].addBotDialog(text);
    }

    public <T> void displayList(List<T> list) {
        String listString = getListAsString(list);
        displayString(listString);
    }

    public void consoleOutput(String s) {
        System.out.println(SEPARATOR);
        System.out.println(s);
        System.out.println(SEPARATOR);
    }
}
