package specobj;

import java.util.Map;
import nbrb.core.SpecObj;
import org.sikuli.script.Pattern;
import nbrb.core.IComfirm;

public class DeleteNodeComfirm extends SpecObj implements IComfirm {

    public <SPF, Coordinates extends Map<String, int[]>> DeleteNodeComfirm(SPF inst, Coordinates mass) {
        super(inst, mass);
    }

    @Override
    public Pattern pressOk() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }

    @Override
    public Pattern pressNo() {
        int[] tmp = setCoordinates();
        this.get().targetOffset(tmp[0], tmp[1]);
        return this.get();
    }

}
