package Model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author phamm
 */
public class Words extends ArrayList<String> {
    private String fName = "input.txt";
    private String saveFile = "output.txt";

    public Words() {
        super();
        loadFile();
    }

    public Words(String fName) {
        super();
        this.fName = fName;
        loadFile();
    }

    public Words(String fName, String saveFile) {
        super();
        this.fName = fName;
        this.saveFile = saveFile;
        loadFile();
    }

    public void loadFile() {
        try {
            System.out.println("Loading " + fName);
            if (!new File(fName).exists()) throw new Exception("File not found!");
            Scanner sc = new Scanner(new FileReader(fName));
            while (sc.hasNextLine()) {
                String inputs = sc.nextLine();
                if (!"".equals(inputs)) {
                    this.add(inputs);
                }
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            System.exit(1);
        }
    }

    public void doSave() throws IOException {
        System.out.println("Saving at " + saveFile);
        File f = new File(saveFile);
        if (!f.exists()) {
            f.createNewFile();
        }
        try (PrintWriter printWriter = new PrintWriter(new File(saveFile))) {
//            printWriter
            for (String s : this) {
                printWriter.println(s);
            }
        } catch (Exception e) {
            System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
        }
    }

    @Override
    public String toString() {
        System.out.println("Printing");
        for (String s : this) {
            System.out.print(s);
        }
        System.out.print("\nTotal: " + this.size());
        return "";
    }

}
