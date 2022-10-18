package markup;

import java.util.List;

public class Emphasis extends AbstractText implements Markdown {
    public Emphasis(List<AbstractText> elements) {
        super(elements, "*", "\\emph");
    }

    public Emphasis(String txt) {
        super(txt, "*", "\\emph");
    }
}
