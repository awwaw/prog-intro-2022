package markup;

import java.util.List;

public class Text extends AbstractText {
    private String text;

    public Text(String txt) {
        super(List.of(), "", "");
        this.text = txt;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append(text);
    }
}
