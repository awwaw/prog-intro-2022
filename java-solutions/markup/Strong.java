package markup;

import java.util.List;

public class Strong extends AbstractText implements Markdown {
    public Strong(List<AbstractText> elements) {
        super(elements, "__", "\\textbf", "");
    }

    public Strong(String txt) {
        super(List.of(), "__", "\\textbf", txt);
    }
}
