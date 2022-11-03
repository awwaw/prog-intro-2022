package markup;

import java.util.List;

public class Strikeout extends AbstractText {
    public Strikeout(List<AbstractText> elements) {
        super(elements, "~", "\\textst");
    }
}
