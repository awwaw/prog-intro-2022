package markup;

public interface Markdown extends TexElement {
    void toMarkdown(StringBuilder sb);

    String toMarkdownString();

}
