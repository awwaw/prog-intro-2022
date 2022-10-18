package markup;

public interface Markdown extends TexElement {
    void toMarkdown(StringBuilder sb);

    void toTex(StringBuilder sb);

    String toMarkdownString();

    String toTexString();
}
