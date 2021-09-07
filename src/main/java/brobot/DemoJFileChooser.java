package brobot;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class DemoJFileChooser extends JPanel {

    private JFileChooser chooser;
    private Storage currentStorage;
    private String choosertitle = "Select save location";

    public DemoJFileChooser(Storage currentStorage) {
        this.currentStorage = currentStorage;
    }

    public File selectFile() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        else {
            return currentStorage.getCurrentFile();
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
