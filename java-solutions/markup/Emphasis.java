package markup;

import java.util.List;

public class Emphasis extends AbstractText {
    public Emphasis(List<AbstractText> elements) {
        super(elements, "*", "\\emph");
    }
}
