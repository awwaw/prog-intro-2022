package markup;

public interface TexElement {
    void toTex(StringBuilder sb);

    String toTexString();
}
