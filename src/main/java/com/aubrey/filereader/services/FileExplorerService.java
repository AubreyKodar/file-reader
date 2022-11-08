package com.aubrey.filereader.services;

import java.io.File;
import java.time.ZoneId;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import java.nio.file.attribute.FileTime;
import org.springframework.stereotype.Service;
import java.nio.file.attribute.BasicFileAttributes;
import com.aubrey.filereader.models.FileListResult;

@Service
@Log4j2
public class FileExplorerService {

    public ArrayList<FileListResult> getFiles(String dirName, int limit, int skip) throws IOException {
        ArrayList<FileListResult> fileListResults = new ArrayList<>();

        File directory = new File(dirName);

        if (!(directory.exists() && directory.isDirectory())) {
            throw new IOException("The directory " + dirName + " does not exist");
        }

        Files.list(directory.toPath())
                .limit(limit)
                .skip(skip)
                .forEach(path -> {
                    try {
                        BasicFileAttributes basicFileAttributes = this.getFileAttributes(String.valueOf(path));

                        fileListResults.add(new FileListResult(
                                String.valueOf(path),
                                fileTimeToDateTime(basicFileAttributes.creationTime()),
                                fileTimeToDateTime(basicFileAttributes.lastAccessTime()),
                                fileTimeToDateTime(basicFileAttributes.lastModifiedTime()),
                                basicFileAttributes.size(),
                                basicFileAttributes.isRegularFile(),
                                basicFileAttributes.isDirectory(),
                                basicFileAttributes.isOther(),
                                basicFileAttributes.isSymbolicLink()
                        ));

                    } catch (IOException e) {
                        log.error("Failed to get file attributes for path " + path, e);
                        throw new RuntimeException(e);
                    }
                });

        return fileListResults;
    }

    private BasicFileAttributes getFileAttributes(String path) throws IOException {
        return Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
    }

    private LocalDateTime fileTimeToDateTime(FileTime fileTime)
    {
        return fileTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
