package markup;

import java.util.List;

public class Emphasis extends AbstractText implements Markdown {
    public Emphasis(List<Markdown> elements) {
        super(elements, "*");
    }

    public Emphasis(String txt) {
        super(txt, "*");
    }
}
