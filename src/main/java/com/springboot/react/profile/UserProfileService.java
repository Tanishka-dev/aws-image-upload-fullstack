package com.springboot.react.profile;

import com.springboot.react.bucket.BucketName;
import com.springboot.react.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService,
                              FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }
    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }


    List<UserProfile> getUserProfiles(){
        return userProfileDataAccessService.getUserProfiles();
    }
    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user= getUserProfileOrThrow(userProfileId);
        String path=String.format("%s/%s",
                BucketName.PROFILE_IMAGE.getBucketName(),
                user.getUserProfileId(),
                user.getUserProfileImageUrl());
        return user.getUserProfileImageUrl()
                .map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //check if file is empty
        if(file.isEmpty()){
            throw new IllegalStateException("Can't upload file ["+file.getSize()+"]");
        }

        //file is an image
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(),IMAGE_GIF.getMimeType(),IMAGE_PNG.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("File must be an image");
        }

        //The user exists in our Database
       UserProfile user= userProfileDataAccessService
                .getUserProfiles()
                .stream()
                .filter(userProfile-> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(
                        ()-> new IllegalStateException(String
                                .format("User profile %s not found", userProfileId)));

       //Grab metadata from file if any
       Map<String, String> metadata = new HashMap();
       metadata.put("Content_Type", file.getContentType());
       metadata.put("Content_Length", String.valueOf(file.getSize()));

       //Store the image in S3 and update db with S3 image link
        String path=String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), user.getUserProfileId());
        String filename=String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path,filename, Optional.of(metadata),file.getInputStream());
            user.setUserProfileImageUrl(filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }


    }


}
