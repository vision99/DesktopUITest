
package nbrb.core;

import com.google.common.collect.HashBiMap;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.sikuli.script.Pattern;
import sikulitest.PatternManager;

public class SpecObj extends PatternManager {

    private Map<String, int[]> arr = null;

    protected int[] setCoordinates() {
//        Method[] meth = this.getClass().getMethods();
        String key = "";
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for(StackTraceElement rt : stackTrace) System.out.println(rt.getMethodName());
//        System.exit(0);
        key = stackTrace[2].getMethodName();
        System.out.println("key: "+key);
        if (arr.containsKey(key)) {
            return arr.get(key);
        }
        return null;
    }

    public <SPF, Coordinates extends Map<String, int[]>> SpecObj(SPF inst, Coordinates mass) {
        super(inst);
        arr = new HashMap<>(mass);
    }
    
    public Pattern change(Pattern inst){
        this.changePattern(inst);
        return this.get();
    }
    public void print() {
        String str = String.format("filename: %s offset: %s", this.get().getFilename(), this.get().getTargetOffset());
        System.out.println(str);
    }
}
