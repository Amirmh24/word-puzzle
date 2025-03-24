public class Word {
    private final String content;
    private final Dir dir;

    public Word(String content,int type) {
        this.content = content;
        dir=new Dir(type);
    }

    public char charAt(int k) {
        return content.charAt(k);
    }

    public String getContent() {
        return content;
    }

    public Dir getDir() {
        return dir;
    }

    public int length() {
        return content.length();
    }


}
