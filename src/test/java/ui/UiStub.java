package ui;

import java.util.List;

/**
 * Stub for User Interface, just print out.
 */
public class UiStub implements IUi {
    @Override
    public void printSentence(String sentence) {
        System.out.println(sentence);
    }
    
    @Override
    public <T> void printIndexedList(List<T> list) {
        int counter = 1;
        for (T item : list) {
            System.out.println(counter++ + item.toString());
        }
    }
}
