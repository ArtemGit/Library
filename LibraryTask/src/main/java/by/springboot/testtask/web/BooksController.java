package by.springboot.testtask.web;

import by.springboot.testtask.domain.Book;
import by.springboot.testtask.domain.LRUCache;
import by.springboot.testtask.reposiroty.BookRepository;
import by.springboot.testtask.reposiroty.FileContentInterface;
import jersey.repackaged.com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FileContentInterface content;

    @Autowired
    private LRUCache<Integer, byte[]> lruCacheContent;

    @Autowired
    private LRUCache<Integer, String> lruCacheContentFileName;

    ////////////////////-----Get List Library Books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> listAll(HttpServletRequest request) {

        List<Book> booksList = Lists.newArrayList(bookRepository.findAll());
        if (booksList.isEmpty()) {
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Book>>(booksList, HttpStatus.OK);
    }

    ////////////////////-----Delete book from library
    @RequestMapping(value = "/books/{idbook}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteBook(@PathVariable("idbook") int id, HttpServletRequest request) {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            System.out.println("Unable to delete. Book with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookRepository.delete(id);
        String pathToLibraryFiles = request.getSession().getServletContext().getRealPath("/resources");
        String pathToZip = pathToLibraryFiles.substring(0, pathToLibraryFiles.indexOf(File.separator)) + File.separator;
        //delete from directory
        content.deleteZipFile(pathToZip, Integer.toString(id));
        //delete from cache
        lruCacheContent.remove(id);
        lruCacheContentFileName.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ////////////////////----- Get 0ne book
    @RequestMapping(value = "/books/{idbook}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable("idbook") int idbook) {
        Book book = bookRepository.findOne(idbook);
        if (book == null) {
            System.out.println("Book with id " + idbook + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    //////////////////////----- Add Book
    @RequestMapping(value = "/books/uploadBook/{idBook}", method = RequestMethod.POST)
    public ModelAndView addBookContent(HttpServletRequest request, @RequestParam("bookContent") MultipartFile file,
                                       @PathVariable("idBook") int idBook) throws IOException {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        if (!file.isEmpty()) {
            if (bookRepository.findOne(idBook) != null) {
                String destination = request.getSession().getServletContext().getRealPath("/resources/Library");

                File gettingFile = content.convert(file, destination);

                content.toZip(gettingFile, idBook);
                gettingFile.delete();
            }
        }

        return model;
    }

    //////////////////////----- Add Book Image
    @RequestMapping(value = "/books/upload/image/book/{idBook}", method = RequestMethod.POST)
    public ModelAndView addBookImage(HttpServletRequest request, @RequestParam("image") MultipartFile file,
                                     @PathVariable("idBook") int idBook) throws IOException {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        if (!file.isEmpty()) {
            Book book = bookRepository.findOne(idBook);
            if (book != null) {
                String str = "";
                if (!file.isEmpty()) {
                    BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                    str = file.getName() + "_" + idBook + file.getOriginalFilename()
                            .substring(file.getOriginalFilename().indexOf('.'), file.getOriginalFilename().length());
                    File directory = new File(request.getSession().getServletContext().getRealPath("/resources/Library/Book_Images"));
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    File destination = new File(directory, str);
                    ImageIO.write(src, str.substring(str.indexOf('.') + 1, str.length()), destination);
                    book.setImageName(str);
                    bookRepository.save(book);

                }
            }
        }
        return model;
    }

    //////////////////////----- Add Book
    @RequestMapping(value = {"/books/{idbook}", "/books"}, method = RequestMethod.POST)
    public ModelAndView addBook(@RequestBody Book book) throws IOException {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        bookRepository.save(book);

        return model;
    }
///////////////////////Document book

    @RequestMapping(value = {"/document","/document/{idbook}"}, method = RequestMethod.GET, produces = "application/octet-stream")
    public ResponseEntity<InputStreamResource> getFile(HttpServletRequest request,@RequestParam("idbook") int idbook) throws IOException {


        String pathToLibraryFiles = request.getSession().getServletContext().getRealPath("/resources/Library");
        File folder = new File(pathToLibraryFiles);
        //Zip saves in the parent folder if was F:\Folder\Folder2\File.pdf => zip will be in F:\
        String pathToZip = folder.getPath().substring(0, folder.getPath().lastIndexOf(File.separator)) + File.separator;
        int countBooksInLibrary = content.countZipFilesInLibrary(pathToZip);
        File zipFolder = new File(pathToZip);
        int cacheSize = lruCacheContent.getCacheSize();
        //если кэш пустой
        if (countBooksInLibrary > cacheSize) {
            lruCacheContent = new LRUCache(countBooksInLibrary);
            lruCacheContentFileName = new LRUCache(countBooksInLibrary);
        }
        byte[] fileContentCache = lruCacheContent.get(idbook);
        //в кэшe нет файла
        if (fileContentCache == null) {
            try {
                String archiveBook = zipFolder.getPath() + idbook + ".zip";
                File fileContent = content.getFileFromZip(archiveBook);
                if (fileContent.exists()) {
                    lruCacheContent.put(idbook, content.convertFileToByte(fileContent));
                    lruCacheContentFileName.put(idbook, fileContent.getName());
                    HttpHeaders headers = collectHttpHeaders(fileContent.getName(), false);
                    fileContent.delete();
                    return collectResponce(headers, content.convertFileToByte(fileContent));
                } else {
                    System.err.println("Запрашиваемого файла нет");
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (IOException e) {
                System.err.println("Файл свойств отсуствует!");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        //в кэше есть файл
        else {
            try {
                HttpHeaders headers = collectHttpHeaders(lruCacheContentFileName.get(idbook), true);

                return collectResponce(headers, fileContentCache);
            } catch (FileNotFoundException e) {
                System.err.println("Файл  отсуствует!");
            }
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpHeaders collectHttpHeaders(String fileName, boolean useCache) {
        Path source = Paths.get(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("x-filename", fileName);
        headers.add("use-cache", useCache ? "yes" : "no");
        try {
            headers.add("mime", Files.probeContentType(source));
            System.out.println("MIME " + Files.probeContentType(source));
        } catch (IOException e) {
            System.out.println("Некорректный путь");
        }
        return headers;
    }

    private ResponseEntity collectResponce(HttpHeaders headers, byte[] file) throws FileNotFoundException {
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file);
    }

    @RequestMapping(value = "/document/delete/{idbook}", method = RequestMethod.DELETE)
    public ResponseEntity<Book> deleteContent(@PathVariable("idbook") int idbook, HttpServletRequest request) {
        File folder = new File(request.getSession().getServletContext().getRealPath("/resources"));
        String pathToZip = folder.getPath().substring(0, folder.getPath().indexOf(File.separator)) + File.separator;

        String contentBook = content.findFileByName(pathToZip, Integer.toString(idbook));
        File f = new File(pathToZip + contentBook);
        if (f.exists())
            if (!f.delete())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ////////////////////-----Update basic information book from library
   /* @RequestMapping(value = "/books/{idbook}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("idbook") int id, @RequestBody Book bookupdate) {

        Book book = bookRepository.findOne(id);
        if (book == null) {
            System.out.println("Unable to update. Book with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setDescription(bookupdate.getDescription());
       /* book.setYear(bookupdate.getYear());
        book.setAuthor(bookupdate.getAuthor());
        book.setName(bookupdate.getName());*/
    /*    bookRepository.save(book);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
    ////////////////////-----Filter
    @RequestMapping(value = "/filter/start", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> filter(@RequestParam(value="issn", required=false,defaultValue = "") String issn,
                                             @RequestParam(value="publicationPlace", required=false,defaultValue = "") String publicationPlace,
                                             @RequestParam(value="name", required=false,defaultValue = "") String name,
                                             @RequestParam(value="publishHouse", required=false,defaultValue = "") String publishHouse,
                                             @RequestParam(value="publishStartYear", required=false,defaultValue = "") String publishStartYear,
                                             @RequestParam(value="publishEndYear", required=false,defaultValue = "") String publishEndYear,
                                             @RequestParam(value="signed", required=false,defaultValue = "") String signed) {

        List<Book> booksList = Lists.newArrayList(bookRepository.findAll());
        if (booksList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        try {
            return new ResponseEntity<>(booksList.stream().filter(book ->
                    (StringUtils.isEmpty(issn) ? true: book.getIssn() == Integer.parseInt(issn)) &&
                            (StringUtils.isEmpty(publicationPlace)? true: book.getPublicationPlace().matches(".*" + publicationPlace + ".*")) &&
                            (StringUtils.isEmpty(name) ? true: book.getName().matches(".*" + name + ".*")) &&
                            (StringUtils.isEmpty(publishHouse) ? true: book.getPublishHouse().matches(".*" + publishHouse + ".*")) &&
                            (StringUtils.isEmpty(publishStartYear) ? true: book.getPublishStartYear() == Integer.parseInt(publishStartYear)) &&
                            (StringUtils.isEmpty(publishEndYear) ? true: book.getPublishEndYear() == Integer.parseInt(publishEndYear)) &&
                            (StringUtils.isEmpty(signed) ? true: book.getSigned().equals(Boolean.parseBoolean(signed))))
                    .collect(Collectors.toList()), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }


}
