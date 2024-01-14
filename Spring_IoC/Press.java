package Spring_IoC;

public class Press {
    private String title;
    private String content;
    private String author;

    // Constructors, getters, setters, and toString methods
    public Press(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Press() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public  void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return  author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Press{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
