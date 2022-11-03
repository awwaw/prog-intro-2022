package markup;

import java.util.List;

public abstract class AbstractText implements Markdown {
    protected String markdownPrefix;
    protected String texPrefix;
    private List<AbstractText> elements;

    protected AbstractText(List<AbstractText> elements, String markdownPrefix, String texPrefix) {
        this.elements = elements;
        this.markdownPrefix = markdownPrefix;
        this.texPrefix = texPrefix;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(markdownPrefix);
        for (AbstractText el : elements) {
            el.toMarkdown(sb);
        }
        sb.append(markdownPrefix);
    }

    public void toTex(StringBuilder sb) {
        sb.append(texPrefix).append("{");
        for (AbstractText el : elements) {
            el.toTex(sb);
        }
        sb.append("}");
    }
}
