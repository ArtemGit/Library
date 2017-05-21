package by.springboot.testtask.domain;


import by.springboot.testtask.reposiroty.FileContentInterface;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileContentImpl implements FileContentInterface {


    public String findFileByName(String startPath, String nameFile) {
        File[] filesList;
        File filesPath = new File(startPath); // создаем объект на папку с файлами

        filesList = filesPath.listFiles(); // записываем файлы из папки в массив объектов типа File

        for (int i = 0; i < filesList.length; i++) {
            if (!filesList[i].isDirectory()) {
                String fname = filesList[i].getName();
                int posPoint = fname.lastIndexOf(".");
                if ((fname.substring(0, posPoint).equals(nameFile)) && !(fname.substring(posPoint, fname.length()).equals(".zip"))) {
                    return fname;
                }
            }
        }
        return null;
    }

    public File getFileFromZip(String pathTozipFile) throws IOException {

        // Получаем содержимое ZIP архива

        try (final ZipInputStream zis = new ZipInputStream(new FileInputStream(pathTozipFile));) {
            ZipEntry ze = zis.getNextEntry();
            for (String nextFileName; ze != null; ze = zis.getNextEntry()) {
                nextFileName = ze.getName();
                File nextFile = new File(pathTozipFile.substring(0, pathTozipFile.lastIndexOf(File.separator)) + File.separator + nextFileName);
                // Записываем содержимое файла
                try (FileOutputStream fos = new FileOutputStream(nextFile)) {
                    int length;
                    System.out.println(ze.getSize());
                    byte[] buffer = new byte[1024];
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    return nextFile;
                }


            }
        }
        return null;
    }

    public boolean deleteZipFile(String startPath,String nameFile)
    {
        File[] filesList;
        File filesPath = new File(startPath); // создаем объект на папку с файлами

        filesList = filesPath.listFiles(); // записываем файлы из папки в массив объектов типа File

        for (int i = 0; i < filesList.length; i++) {
            if (!filesList[i].isDirectory()) {
                String fname = filesList[i].getName();
                int posPoint = fname.lastIndexOf(".");
                if ((fname.substring(0, posPoint).equals(nameFile)) && (fname.substring(posPoint, fname.length()).equals(".zip"))) {
                    filesList[i].delete();
                    return true;
                }
            }
        }
        return false;
    }
    public void toZip(File file,int idBook) throws IOException {
        String fileNmae=file.getName();
        String fpath=file.getPath();
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fpath.substring(0,fpath.lastIndexOf(File.separator))+File.separator+idBook+".zip"));

            FileInputStream fis= new FileInputStream(fpath);)
        {
            zout.setLevel(9);
            ZipEntry entry1=new ZipEntry(fileNmae);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
            file.delete();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    public int countZipFilesInLibrary(String pathDirectory) {
        int count=0;

        File[] filesList;
        File filesPath = new File(pathDirectory); // создаем объект на папку с файлами
        filesList = filesPath.listFiles(); // записываем файлы из папки в массив объектов типа File
        for (int i = 0; i < filesList.length; i++) {
            if (!filesList[i].isDirectory()) {
                String fname = filesList[i].getName();
                int posPoint = fname.lastIndexOf(".");
                if ((fname.substring(posPoint, fname.length() ).equals(".zip"))) {
                    count++;
                }
            }
        }
        return count;
    }
    public byte[] convertFileToByte(File f)
    {
        Path path = Paths.get(f.getPath());
        try {
            byte[] data = Files.readAllBytes(path);
            return data;
        } catch (IOException e) {
            System.out.println("Ошибка преобразования файла в массив байт, не найден");
        }
        return null;
    }

    public File convert(MultipartFile file,String path) throws IOException {

        File folderDestination=new File(path);

        if (!folderDestination.exists()) {
            folderDestination.mkdirs();
        }

        File convFile = new File(folderDestination+File.separator+file.getOriginalFilename());
        System.out.println("Created getting file? "+convFile.createNewFile());
        FileOutputStream fos = new FileOutputStream(convFile);
        byte[] buffer = file.getBytes();
        fos.write(buffer, 0, buffer.length);
        fos.close();
        return convFile;
    }
}
