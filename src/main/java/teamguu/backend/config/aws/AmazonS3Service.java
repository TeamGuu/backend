package teamguu.backend.config.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teamguu.backend.exception.situation.EmptyFileException;
import teamguu.backend.exception.situation.FileUploadFailureException;

import java.io.InputStream;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class AmazonS3Service {

    private static final String FILE_EXTENSION_SEPARATOR = ".";
    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadFile(MultipartFile multipartFile) {
        validateFileExists(multipartFile);
        String fileName = createFileName(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
        } catch (Exception e) {
            throw new FileUploadFailureException(e.getCause());
        }
        return fileName;
    }

    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
    }


    private void validateFileExists(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new EmptyFileException();
        }
    }

    private String createFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR); //파일 확장자 구분선
        String fileExtension = originalFileName.substring(fileExtensionIndex); //파일 확장자
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        return fileName + "_" + now + fileExtension;
    }
}
