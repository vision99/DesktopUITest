package sikulitest;

import org.sikuli.script.Pattern;
import java.io.File;

public abstract class PatternManager {

    private Pattern ptrn=null;
    
    public PatternManager() {

    }

    protected <SPF> PatternManager(SPF inst) {
        if (inst instanceof String) {
             ptrn = new Pattern ((String)inst);
        } else if (inst instanceof Pattern) {
            ptrn = new Pattern ((Pattern)inst);
        } else if (inst instanceof File) {
            ptrn = new Pattern (((File)inst).getAbsolutePath());
        } else {
            try {
                throw new RuntimeException(String.format("Sikulitest: String, Pattern, File: invalid parameter: %s", inst));
            } catch (RuntimeException ex) {
                System.out.println("\033[31m" + ex.getMessage());
            }
        }
    }
    public void changePattern(Pattern inst){
        ptrn = new Pattern(inst);
    
    }
    protected Pattern get(){
        return this.ptrn;
    }
}
