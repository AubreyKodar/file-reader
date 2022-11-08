package com.aubrey.filereader.controllers;

import java.util.ArrayList;
import java.io.IOException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.aubrey.filereader.models.FileListResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.aubrey.filereader.services.FileExplorerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

@RestController
@Log4j2
@RequestMapping("/api/v1/")
public class FileExplorerController {
    @GetMapping("get-file-listing")
    public ResponseEntity<Object> getFilesListing(
            FileExplorerService fileExplorerService,
            @RequestParam @Required String dirName,
            @RequestParam @Required int limit,
            @RequestParam @Required int skip
    ) {
        try {

            log.info("Listing files under directory " + dirName + " with limit " + limit + " and skip " + skip);

            ArrayList<FileListResult> fileListResults =  fileExplorerService.getFiles(dirName, limit, skip);

            log.info("Directory " + dirName + " contains " + fileListResults.size() + " files");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(fileListResults);

        }
        catch (IOException exception) {
            log.error("Could not list files at " + dirName, exception);

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(exception.getMessage());
        }
    }
}
