package markup;

import java.util.List;

public class Strong extends AbstractText {
    public Strong(List<AbstractText> elements) {
        super(elements, "__", "\\textbf");
    }
}
