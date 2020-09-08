package sikulitest;

import org.sikuli.script.Pattern;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

public class PatternFinder {

    public static Pattern findPattern(String tmpDir, String str){
        Pattern ptrn = null;
        String dir = System.getProperty(tmpDir);
        File file = new File(dir);
        if (file.isDirectory()) {
            dir += "\\" + str + ".png";
            file = new File(dir);
            if (file.exists() && file.isFile()) {
                ptrn = new Pattern(file.getAbsolutePath()).similar(0.9);
            } else {
                try {
                    throw new FileNotFoundException("file: " + file + " not found!");
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            try {
                throw new NotDirectoryException(file + " is not directory");
            } catch (NotDirectoryException e) {
                System.out.println(e.getMessage());
            }
        }
        return ptrn;
    }
}
