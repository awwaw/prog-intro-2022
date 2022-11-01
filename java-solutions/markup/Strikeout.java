package markup;

import java.util.List;

public class Strikeout extends AbstractText implements Markdown {
    public Strikeout(List<AbstractText> elements) {
        super(elements, "~", "\\textst", "");
    }

    public Strikeout(String txt) {
        super(List.of(), "~", "\\textst", txt);
    }
}
