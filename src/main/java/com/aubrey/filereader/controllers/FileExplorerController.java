package com.aubrey.filereader.controllers;

import java.util.ArrayList;
import com.aubrey.filereader.models.FileListResult;
import com.aubrey.filereader.services.FileExplorerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class FileExplorerController {
    @GetMapping("get-file-listing")
    public ArrayList<FileListResult> getFilesListing(FileExplorerService fileExplorerService)
    {
        return fileExplorerService.getFiles();
    }
}
