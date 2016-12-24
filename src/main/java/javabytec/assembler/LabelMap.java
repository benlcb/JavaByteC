package javabytec.assembler;

import org.objectweb.asm.Label;
import java.util.HashMap;

public class LabelMap {

    private HashMap<String, Label> labels;

    public LabelMap(){
        labels = new HashMap<String, Label>();
    }

    public Label getLabel(String name) {

        if (!name.startsWith("#")) name += '#';

        Label l = labels.get(name);

        if (l == null){
            l = new Label();
            labels.put(name, l);
        }

        return l;
    }

    public Label[] getLabels(String names){

        String[] names_split = names.replaceAll("[{}]","")
                .split(" ");

        Label[] labels = new Label[names_split.length];

        for (int i = 0; i<labels.length; i++) {
            labels[i] = this.getLabel(names_split[i]);
        }

        return labels;
    }


}
