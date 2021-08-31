package ui;

import ui.view.MainWindow;

import java.util.List;

public class GuiUiImpl implements IUi {
    private final MainWindow mainWindow;
    
    public GuiUiImpl(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
    
    @Override
    public void printSentence(String sentence) {
        mainWindow.addBotDialog(sentence);
    }
    
    @Override
    public <T> void printIndexedList(List<T> list) {
        String formattedList = formatListToString(list);
        mainWindow.addBotDialog(formattedList);
    }
    
    public <T> String formatListToString(List<T> list) {
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
        if (list.size() > 0) string.deleteCharAt(string.length() - 1);
        
        return string.toString();
    }
}
