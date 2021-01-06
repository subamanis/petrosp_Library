package com.petrosp.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public final class PFiles
{
    private PFiles() { }


    /**
     * @param path The directory path to the file with the name of the file.
     * @return a byte[] containing all the data of the file as bytes.
     */
    public static byte[] readBytes(final String path)
    {
        return readBytes(new File(path));
    }

    /**
     * @param file A File made from the directory path to the file with the name of the file.
     * @return a byte[] containing all the data of the file as bytes.
     */
    public static byte[] readBytes(final File file)
    {
        byte[] buffer = new byte[(int) file.length()];

        try (BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file))) {
            buf.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer;
    }


    /**
     * @param path The directory path to the file with the name of the file.
     * @param chunkSize The total size that the chunks of data will be split.
     * @return A List<byte[]> containing chunks of byte data.
     */
    public static List<byte[]> readBytes(final String path, final int chunkSize)
    {
        return readBytes(new File(path), chunkSize);
    }

    /**
     * @param file A File made from the directory path to the file with the name of the file.
     * @param chunkSize The total size that the chunks of data will be split.
     * @return A List<byte[]> containing chunks of byte data.
     */
    public static List<byte[]> readBytes(final File file, final int chunkSize)
    {
        List<byte[]> chunks = new ArrayList<>();

        int remainingSize = (int) file.length();
        int chunkCounter = 0;

        try (BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file))) {
            while (remainingSize > 0){
                int currentChunkSize = Math.min(remainingSize, chunkSize);

                chunks.add(new byte[currentChunkSize]);
                buf.read(chunks.get(chunkCounter++));

                remainingSize -= currentChunkSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return chunks;
    }

    /**
     * @param path The directory path to the file WITHOUT the name of a file. Dots in the path not accepted.
     * @return {@code true} Only if the directory was created or existed.
     */
    public static boolean createDirectoryIfMissing(final String path)
    {
        return createDirectoryIfMissing(new File(path));
    }

    /**
     * @param file  A File made from the directory path to the file WITHOUT the name of a file. Dots in the path not accepted.
     * @return {@code true} Only if the directory was created or existed.
     */
    public static boolean createDirectoryIfMissing(final File file)
    {
        if(!file.isDirectory()){
            return file.mkdir();
        }

        return true;
    }

    /**
     * @param path The directory path to the file WITHOUT the name of a file. Dots in the path not accepted.
     * @return {@code true} Only if the directories were created or existed.
     */
    public static boolean createDirectoryAndParentsIfMissing(final String path)
    {
        return createDirectoryAndParentsIfMissing(new File(path));
    }

    /**
     * @param file A File made from the directory path to the file WITHOUT the name of a file. Dots in the path not accepted.
     * @return {@code true} Only if the directory were created or existed.
     */
    public static boolean createDirectoryAndParentsIfMissing(final File file)
    {
        if(!PStrings.extractFileName(file.getPath()).equals("")){
            return false;
        }

        if(!file.isDirectory()){
            return file.mkdirs();
        }

        return true;
    }


    /**
     * @param dirPath The directory path. Dots in the path not accepted.
     * @param fileName The file name.
     * @return {@code true} Only if the directories and the file were created or existed.
     */
    public static boolean createDirectoriesAndFileIfMissing(final String dirPath, final String fileName)
    {
        if(!createDirectoryAndParentsIfMissing(dirPath)){
            return false;
        }

        File file = new File(dirPath, fileName);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @param file A File made from the directory path to the file with the name of the file. Dots in the directory path not accepted.
     * @return {@code true} Only if the directories and the file were created or existed.
     */
    public static boolean createDirectoriesAndFileIfMissing(final File file)
    {
        String filePath = file.getAbsolutePath();
        return createDirectoriesAndFileIfMissing(PStrings.extractPath(filePath), PStrings.extractFileName(filePath));
    }


    /**
     * @param fileName The name of the file without directories.
     * @return {@code true} Only if the file was created or existed.
     */
    public static boolean createFileIfMissing(final String fileName)
    {
        return createFileIfMissing(new File(fileName));
    }

    /**
     * @param file A File containing the name of the file without directories.
     * @return {@code true} Only if the file was created or existed.
     */
    public static boolean createFileIfMissing(final File file)
    {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * Copies the contents of a file to an already existing file.
     * @param source An (existing) File made from the directory path to the source file with the name of the file.
     * @param copy An (existing) File made from the directory path to the target file with the name of the file.
     * @return {@code true} Only if the provided files were valid and the contents were copied.
     */
    public static boolean copyFileContents(final File source, final File copy)
    {
        Path copied = copy.toPath();
        Path originalPath = source.toPath();
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a new file and the appropriate parent directories if missing, with the contents of the original file.
     * @param source An (existing) File made from the directory path to the source file with the name of the file.
     * @param copy A File made from the directory path and the name of the file to be created.
     * @return {@code true} Only if the source file was valid, the copy file could be created and the contents could be copied.
     */
    public static boolean copyFile(final File source, final File copy)
    {
        if(!copy.isFile()){
            createDirectoriesAndFileIfMissing(copy);
        }

        return copyFileContents(source, copy);
    }


    /**
     * Removes any contents from a file.
     * @param filePath The directory path to the file with the name of the file.
     * @return {@code true} Only if the provided path was valid and the contents could be cleared.
     */
    public static boolean clearFile(final String filePath)
    {
        return clearFile(new File(filePath));
    }

    /**
     * Removes any contents from a file.
     * @param file A File containing the directory path to the file with the name of the file.
     * @return {@code true} Only if the provided path was valid and the contents could be cleared.
     */
    public static boolean clearFile(final File file)
    {
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
            pw.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Recursively deletes all the children of the provided directory, along with the parent directory, only if the children do no contain any files.
     * @param file A file containing the name of the parent folder.
     * @return {@code true} Only if the provided file was valid and no files were found inside the children directories.
     */
    public static boolean deleteDirAndChildrenSafe(final File file)
    {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                return file.delete();
            }
            else {
                String[] files = file.list();

                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    deleteDirAndChildrenSafe(fileDelete);
                }
                if (file.list().length == 0) {
                    return file.delete();
                }
            }
        }
        return !file.isDirectory();
    }

    /**
     * Recursively deletes all the children of the provided directory and all the files contained, along with the parent directory,
     * @param file A file containing the name of the parent folder.
     * @return {@code true} Only if the provided file was valid.
     */
    public static boolean deleteDirAndChildren(final File file)
    {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                return file.delete();
            }
            else {
                String[] files = file.list();

                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    deleteDirAndChildren(fileDelete);
                }
                if (file.list().length == 0) {
                    return file.delete();
                }
            }
        }
        else if(file.isFile()){
            return file.delete();
        }

        return !file.isDirectory();
    }
}
