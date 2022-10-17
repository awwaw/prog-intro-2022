package markup;

import java.util.List;

public class Strikeout extends AbstractText implements Markdown {
    public Strikeout(List<Markdown> elements) {
        super(elements, "~");
    }

    public Strikeout(String txt) {
        super(txt, "~");
    }
}
