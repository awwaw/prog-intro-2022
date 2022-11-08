package markup;

import java.util.List;

public abstract class AbstractList implements ElementsSet {
    private String environment;
    private StringBuilder txt = new StringBuilder();

    protected AbstractList(List<ListItem> elements, String environment) {
        this.environment = environment;
        txt.append("\\begin{").append(environment).append("}");
        for (ListItem el : elements) {
            el.toTex(txt);
        }
        txt.append("\\end{").append(environment).append("}");
    }
    public void toTex(StringBuilder sb) {
        sb.append(txt);
    }
}
