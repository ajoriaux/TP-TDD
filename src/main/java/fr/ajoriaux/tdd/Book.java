package fr.ajoriaux.tdd;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private String editor;
    private String format;
    private boolean isAvailable;

    public Book(String isbn, String title, String author, String editor, String format, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.editor = editor;
        this.format = format;
        this.isAvailable = isAvailable;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
}