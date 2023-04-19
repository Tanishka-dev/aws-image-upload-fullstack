package com.springboot.react.bucket;

public enum BucketName {

    PROFILE_IMAGE("aws-image-upload-fullstack");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
