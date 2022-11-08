package com.aubrey.filereader.models;

import lombok.Getter;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class FileListResult {
    private String FilePath;
    private LocalDateTime CreationTime;
    private LocalDateTime LastAccessTime;
    private LocalDateTime LastModifiedTime;
    private Long Size;
    private boolean IsRegularFile;
    private boolean IsDirectory;
    private boolean IsOther;
    private boolean IsSymbolicLink;
}
