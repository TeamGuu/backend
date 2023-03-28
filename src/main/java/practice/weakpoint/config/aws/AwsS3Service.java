package practice.weakpoint.config.aws;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

    private final S3Template s3Template;
    @Value("${aws.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile image) throws IOException {
        String fileName = createFileName(image.getOriginalFilename());
        InputStream inputStream = image.getInputStream();
        S3Resource s3Resource = s3Template.upload(bucket, fileName, inputStream,
                ObjectMetadata.builder().contentType(image.getContentType()).build());
        return s3Resource.getURL().toString();
    }

    public void deleteImage(String imageUrl) {
        s3Template.deleteObject(imageUrl);
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
