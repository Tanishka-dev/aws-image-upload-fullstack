package com.springboot.react.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

// Give S3 client
@Configuration
public class AmazonConfig {
    Dotenv dotenv = Dotenv.load();

    @Bean
    public AmazonS3 s3(){
        AWSCredentials awsCredentials= new BasicAWSCredentials(
                "AKIATL4UL6PBWVETGIW5",
                "bbti2pIuoWHpVXoa9qCMWRD1duczvsuF4CRRk3KX"
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.EU_NORTH_1)
                .build();
    }

}
