package markup;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements ElementsSet, Markdown {
    private final StringBuilder markdownText = new StringBuilder();
    private final StringBuilder texText = new StringBuilder();

    public Paragraph(List<AbstractText> elements) {
        for (AbstractText el : elements) {
//            this.markdownText.append(el.toMarkdownString());
//            this.texText.append(el.toTexString());
            el.toTex(texText);
            el.toMarkdown(markdownText);
        }
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownText);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(texText);
    }
}
