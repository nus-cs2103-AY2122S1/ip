package ui;

import java.util.List;

public class JadenConsoleUi {

    public <T> void printListWithIndexes(List<T> list) {
        String outString = "";
        for (int i = 0; i < list.size(); i++) {
            outString += (i+1) + ". " + list.get(i).toString() + "\n";
        }

        if(!outString.isEmpty()) {
            outString = outString.substring(0, outString.length() - 1);
        }

        
    }
}
