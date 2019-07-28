package bowling.zander.com.cricketbowling;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BowlingCategory {

    public  Map<String, String> deliveryNames = new HashMap();
    public List<String> deliveryCodes = new LinkedList<String>();
    BowlingCategory(){
        deliveryCodes.add("wide");
        deliveryCodes.add("lsp");
        deliveryCodes.add("lgl");
        deliveryCodes.add("lop");
        deliveryCodes.add("msp");
        deliveryCodes.add("mgl");
        deliveryCodes.add("mop");
        deliveryCodes.add("osp");
        deliveryCodes.add("ogl");
        deliveryCodes.add("oop");
        deliveryNames.put("wide" , "Wide          - ");
        deliveryNames.put("lsp" , "ShortPitch(L)  - ");
        deliveryNames.put("msp" , "ShortPitch(M)  - ");
        deliveryNames.put("osp" , "ShortPitch(O)  - ");
        deliveryNames.put("lgl" , "Good Length(L) - ");
        deliveryNames.put("mgl" , "Good Length(M) - ");
        deliveryNames.put("ogl" , "Good Length(O) - ");
        deliveryNames.put("lop" , "Over Pitch(L)  - ");
        deliveryNames.put("mop" , "Over Pitch(M)  - ");
        deliveryNames.put("oop" , "Over Pitch(O)  - ");
    }

    public Map<String, String> getDeliveryNames() {
        return deliveryNames;
    }

    public void setDeliveryNames(Map<String, String> deliveryNames) {
        this.deliveryNames = deliveryNames;
    }

    public List<String> getDeliverCodes() {
        return deliveryCodes;
    }

    public void setDeliverCodes(List<String> deliverCodes) {
        this.deliveryCodes = deliverCodes;
    }
}
