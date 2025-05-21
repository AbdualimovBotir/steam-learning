package uz.steam_learning.models;

import jakarta.persistence.*;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String type;
    private String contentUrl;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Users creator;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;


    public Resource(Long id, String title, String description, String type, String contentUrl, Users creator, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.contentUrl = contentUrl;
        this.creator = creator;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Resource() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Users getCreator() {
        return creator;
    }

    public void setCreator(Users creator) {
        this.creator = creator;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
