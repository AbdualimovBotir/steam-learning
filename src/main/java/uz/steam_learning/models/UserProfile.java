package uz.steam_learning.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
public class UserProfile {
    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Users users;

    private String avatarUrl;
    private String bio;
    private String phone;
    private String address;

    @Lob
    private String socialLinks; // JSON as String

    @UpdateTimestamp
    private Timestamp updatedAt;

    public UserProfile(Long userId, Users users, String avatarUrl, String bio, String phone, String address, String socialLinks, Timestamp updatedAt) {
        this.userId = userId;
        this.users = users;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.phone = phone;
        this.address = address;
        this.socialLinks = socialLinks;
        this.updatedAt = updatedAt;
    }

    public UserProfile() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
