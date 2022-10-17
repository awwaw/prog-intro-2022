package markup;

import java.util.ArrayList;
import java.util.List;

public class Paragraph extends AbstractText implements Markdown {
    public Paragraph(String txt) {
        super(txt, "");
    }
    public Paragraph(List<Markdown> elements) {
        super(elements, "");
    }
}
