package com.springboot.react.profile;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UserProfile {

    private final UUID userProfileId;
    private final String userName;
    private String userProfileImageUrl;
    //s3 link


    public UserProfile(UUID userProfileId, String userName, String userProfileImageUrl) {
        this.userProfileId = userProfileId;
        this.userProfileImageUrl = userProfileImageUrl;
        this.userName = userName;
    }

    //getter and setters
    public UUID getUserProfileId() {
        return userProfileId;
    }

    public String getUserName() {
        return userName;
    }

    public Optional<String> getUserProfileImageUrl() {
        return Optional.ofNullable(userProfileImageUrl);
    }

    public void setUserProfileImageUrl(String userProfileImageUrl) {
        this.userProfileImageUrl = userProfileImageUrl;
    }

    //equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserProfile that = (UserProfile) o;
        return Objects.equals(userProfileId,that.userProfileId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userProfileImageUrl, that.userProfileImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileId, userName, userProfileImageUrl);
    }
}
