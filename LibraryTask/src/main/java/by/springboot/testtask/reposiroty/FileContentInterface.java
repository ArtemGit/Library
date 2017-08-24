package by.springboot.testtask.reposiroty;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Тёма on 25.02.2017.
 */

public interface FileContentInterface {

    String findFileByName(String startPath, String nameFile);

    File getFileFromZip(String pathTozipFile) throws IOException;

    void toZip(File file,int idBook) throws IOException;

    int countZipFilesInLibrary(String pathDirectory);

    byte[] convertFileToByte(File f);

    boolean deleteZipFile(String startPath,String nameFile);

    boolean deleteImageFile(String startPath,String nameFile);

    File convert(MultipartFile file, String path) throws IOException;
}
