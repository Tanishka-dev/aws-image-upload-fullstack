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
        USER_PROFILES.add(new UserProfile(UUID.fromString("3fda9f59-5e6e-4eb5-8be9-6182721e2821"), "Swastika Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("a44efef7-c71d-42c8-8611-96034013d5d4"), "Tanishka Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("6a0a17b7-baf9-4963-b13e-f3db39a5d9ac"), "Archana Yadav", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("51c3f959-e05a-4fc0-b42c-df0a4022d45f"), "Reyansh Yadav", null));
    }
    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }
}

