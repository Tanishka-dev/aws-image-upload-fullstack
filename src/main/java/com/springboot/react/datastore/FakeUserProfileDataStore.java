package com.springboot.react.datastore;

import com.springboot.react.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES= new ArrayList<UserProfile>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Swastika Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Tanishka Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Archana Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Reyansh Yadav", null));
    }
    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}

