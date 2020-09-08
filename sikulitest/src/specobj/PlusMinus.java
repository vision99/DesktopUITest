package specobj;

import java.util.Map;
import nbrb.core.*;
import org.sikuli.script.Pattern;
import sikulitest.PatternManager;

public class PlusMinus extends SpecObj implements IPlusMinus {

    public <SPF, Coordinates extends Map<String, int[]>> PlusMinus(SPF inst, Coordinates mass) {
        super(inst, mass);
    }

    public Pattern plus() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }

    public Pattern minus() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }
}
