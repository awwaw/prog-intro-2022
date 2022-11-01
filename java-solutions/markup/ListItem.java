package markup;

import java.util.List;

public class ListItem implements TexElement {
    protected StringBuilder txt = new StringBuilder();

    public ListItem(String txt) {
        this.txt.append("\\item ");
        this.txt.append(txt);
    }

    public ListItem(List<ElementsSet> elements) {
        txt.append("\\item ");
        for (ElementsSet el : elements) {
            // txt.append(el.toTexString());
            el.toTex(txt);
        }
    }

    public void toTex(StringBuilder sb) {
        sb.append(txt);
    }
}
