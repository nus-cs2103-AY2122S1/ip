package ui;

import ui.view.IWindowController;

import java.util.List;

public class GuiUiImpl implements IUi {
    /**
     * The first window controller in the array is reserved for the main window.
     */
    private final IWindowController[] mainWindow;
    
    public GuiUiImpl(IWindowController... mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    @Override
    public void printSentence(String sentence) {
        mainWindow[0].addBotDialog(sentence);
    }
    
    @Override
    public <T> void printIndexedList(List<T> list) {
        String formattedList = formatListToString(list);
        mainWindow[0].addBotDialog(formattedList);
    }
    
    private <T> String formatListToString(List<T> list) {
        int numbering = 1;
        StringBuilder string = new StringBuilder();
        
        for (T item : list) {
            string.append(" ")
                    .append(numbering)
                    .append(". ")
                    .append(item.toString())
                    .append("\n");
            
            numbering++;
        }
        
        // remove the last extra \n if there is at least an item in the list
        if (list.size() > 0) {
            string.deleteCharAt(string.length() - 1);
        }
        
        return string.toString();
    }
}
