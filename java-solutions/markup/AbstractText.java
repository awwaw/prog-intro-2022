package markup;

import java.util.List;

public abstract class AbstractText implements Markdown {
    protected StringBuilder markdownText = new StringBuilder(); // NOTE модификатор доступа
    protected StringBuilder texText = new StringBuilder();
    protected String markdownPrefix;
    protected String texPrefix;

    protected AbstractText(
        List<AbstractText> elements,
        String markdownPrefix,
        String texPrefix,
        String text
    ) { // NOTE копипаста
        this.markdownPrefix = markdownPrefix;
        this.texPrefix = texPrefix;

        this.markdownText.append(this.markdownPrefix);
        this.texText.append(this.texPrefix);
        if (this.texPrefix.length() > 0) {
            this.texText.append("{");
        }
        if (elements.size() > 0) {
            for (AbstractText el : elements) {
                this.markdownText.append(el.toMarkdownString());
                this.texText.append(el.toTexString());
            }
        } else {
            this.markdownText.append(text);
            this.texText.append(text);
        }
        this.markdownText.append(this.markdownPrefix);
        if (texPrefix.length() > 0) {
            this.texText.append("}");
        }
    }

    public String toTexString() {
        return this.texText.toString();
    }

    public String toMarkdownString() {
        return this.markdownText.toString();
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(this.markdownText);
    }

    ;

    public void toTex(StringBuilder sb) {
        sb.append(this.texText);
    }
}
