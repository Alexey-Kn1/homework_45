package ru.netology.model;

public class PostData {
    private long id;
    private String content;
    private boolean deleted;

    public PostData() {
    }

    public PostData(Post post) {
        this(post.getId(), post.getContent(), false);
    }

    public PostData(long id, String content, boolean deleted) {
        this.id = id;
        this.content = content;
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
