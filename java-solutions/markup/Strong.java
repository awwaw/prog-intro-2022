package markup;

import java.util.List;

public class Strong extends AbstractText implements Markdown {
    public Strong(List<Markdown> elements) {
        super(elements, "__");
    }

    public Strong(String txt) {
        super(txt, "__");
    }
}
