package sikulitest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;
import org.sikuli.script.Pattern;

public class VklVikl extends PatternManager {

    Map<String, int[]> arr = null;

    private int[] setCoordinates() {
        Method[] meth = this.getClass().getMethods();
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

    public <SPF, Coordinates extends Map<String, int[]>> VklVikl(SPF inst, Coordinates mass) {
        super(inst);
        arr = mass;
    }

    public Pattern extend() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }

    public Pattern cross() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }

    public Pattern collapse() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
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
