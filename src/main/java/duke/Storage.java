package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Storage {
    String filepath;

    public Storage(String var1) {
        this.filepath = var1;
    }

    public ArrayList<String[]> loadData() throws FileNotFoundException {
        ArrayList var1 = new ArrayList();

        try {
            BufferedReader var2 = new BufferedReader(new FileReader(this.filepath));

            String var3;
            try {
                while((var3 = var2.readLine()) != null) {
                    String[] var4 = var3.split("\\|");
                    var1.add(var4);
                }
            } catch (Throwable var6) {
                try {
                    var2.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            var2.close();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        return var1;
    }

    public void saveData(ArrayList<Task> var1) throws IOException {
        File var2 = new File(this.filepath);
        FileOutputStream var3 = new FileOutputStream(var2);
        BufferedWriter var4 = new BufferedWriter(new OutputStreamWriter(var3));
        Iterator var5 = var1.iterator();

        while(var5.hasNext()) {
            Task var6 = (Task)var5.next();
            String var7 = var6.getActionName();
            boolean var8 = var6.getCompleted();
            String var9 = var6.getType();
            String var10 = "0";
            if (var8) {
                var10 = "1";
            }

            String var11;
            if (var9.equals("T")) {
                var11 = var9 + " | " + var10 + " | " + var7;
            } else {
                SimpleDateFormat var12 = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date var13 = var6.getDate();
                var11 = var9 + " | " + var10 + " | " + var7 + " | " + var12.format(var13);
            }

            var4.write(var11);
            var4.newLine();
        }

        var4.close();
    }
}
