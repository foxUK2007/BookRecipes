package me.sav.bookrecipes.controllers;

import me.sav.bookrecipes.services.FilesService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FilesService filesService;

    public FileController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/export/recipe")

    public ResponseEntity<InputStreamResource> downloadRecipeFileRc() throws FileNotFoundException {
        File fileDownRc = filesService.getDataFileRc();

        if (fileDownRc.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileDownRc));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileDownRc.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/recipe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileRc(@RequestParam MultipartFile fileUpRc) {
        filesService.cleanDataFileRc();
        File fileDownRc = filesService.getDataFileRc();

        try (FileOutputStream fos = new FileOutputStream(fileDownRc)) {
            IOUtils.copy(fileUpRc.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping("/export/ingredient")

    public ResponseEntity<InputStreamResource> downloadIngredientFileIg() throws FileNotFoundException {
        File fileDownIg = filesService.getDataFileIg();

        if (fileDownIg.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(fileDownIg));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(fileDownIg.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"ingredient.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/ingredient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadDataFileIg(@RequestParam MultipartFile fileUpIg) {
        filesService.cleanDataFileIg();
        File fileDownIg = filesService.getDataFileIg();

        try (FileOutputStream fos = new FileOutputStream(fileDownIg)) {
            IOUtils.copy(fileUpIg.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


//
//        try (BufferedInputStream bis = new BufferedInputStream(fileUp.getInputStream());
//             FileOutputStream fos = new FileOutputStream(fileDown);
//             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
////
////            byte[] buffer = new byte[1024];
////            while (bis.read(buffer) > 0) {
////                bos.write(buffer);
////            }
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }