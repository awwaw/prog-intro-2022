package markup;

import java.util.List;

public abstract class AbstractText implements Markdown {
    protected StringBuilder markdownText = new StringBuilder();
    protected StringBuilder texText = new StringBuilder();
    protected String markdownPrefix;
    protected String texPrefix;

    protected AbstractText(String txt, String markdownPrefix, String texPrefix) {
        this.markdownPrefix = markdownPrefix;
        this.texPrefix = texPrefix;

        this.markdownText.append(markdownPrefix);
        this.markdownText.append(txt);
        this.markdownText.append(markdownPrefix);

        this.texText.append(texPrefix);
        if (this.texPrefix.length() > 0) {
            this.texText.append("{");
        }
        this.texText.append(txt);
        if (texPrefix.length() > 0) {
            this.texText.append("}");
        }
    }

    protected AbstractText(List<AbstractText> elements, String markdownPrefix, String texPrefix) { // NOTE копипаста
        this.markdownPrefix = markdownPrefix;
        this.texPrefix = texPrefix;

        this.markdownText.append(this.markdownPrefix);
        this.texText.append(this.texPrefix);
        if (this.texPrefix.length() > 0) {
            this.texText.append("{");
        }
        for (AbstractText el : elements) {
            this.markdownText.append(el.toMarkdownString());
            this.texText.append(el.toTexString());
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
    };

    public void toTex(StringBuilder sb) {
        sb.append(this.texText);
    }
}
