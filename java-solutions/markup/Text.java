package markup;

import java.util.List;

public class Text extends AbstractText implements Markdown {
    public Text(List<AbstractText> elements) {
        super(elements, "", "", "");
    }

    public Text(String txt) {
        super(List.of(), "", "", txt);
    }
}
