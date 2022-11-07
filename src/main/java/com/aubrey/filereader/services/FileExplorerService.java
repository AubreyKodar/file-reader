package com.aubrey.filereader.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.aubrey.filereader.models.FileListResult;

@Service
public class FileExplorerService {

    public ArrayList<FileListResult> getFiles()
    {
        return new ArrayList<FileListResult>();
    }
}
