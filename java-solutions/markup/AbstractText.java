package markup;

import java.util.List;

public abstract class AbstractText implements Markdown {
    protected StringBuilder text = new StringBuilder();

    protected AbstractText(String txt, String symbols) {
        text.append(symbols);
        text.append(txt);
        text.append(symbols);
    }

    protected AbstractText(List<Markdown> elements, String symbols) {
        text.append(symbols);
        for (Markdown el : elements) {
            text.append(el.toString());
        }
        text.append(symbols);
    }

    public String toString() {
        return text.toString();
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    };
}
