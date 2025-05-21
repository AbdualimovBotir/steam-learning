package uz.steam_learning.dtos;

public class ResourceDto {
    private String title;
    private String description;
    private String type;
    private String contentUrl;
    private Long creatorId; // Users o‘rniga faqat ID yuboriladi

    public ResourceDto() {
    }

    public ResourceDto(String title, String description, String type, String contentUrl, Long creatorId) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.contentUrl = contentUrl;
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
