package markup;

import java.util.List;

public class Text extends AbstractText implements Markdown {
    public Text(List<Markdown> elements) {
        super(elements, "");
    }

    public Text(String txt) {
        super(txt, "");
    }

    public String toString() {
        return text.toString();
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
}
