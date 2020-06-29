package com.petrosp.assist.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


class PFilesTest
{
    private static File readDir;
    private static File testDir1;
    private static File childDir1;

    private static File test1;
    private static File empty;


    @BeforeAll
    static void before()
    {
        readDir = new File("files");
        assertTrue(PFiles.createDirectoryIfMissing(readDir));

        test1 = new File("files/test1.txt");
        empty = new File("files/empty.txt");
        assertTrue(PFiles.createFileIfMissing(test1));
        assertTrue(PFiles.createFileIfMissing(empty));

        testDir1 = new File("files/testDir1");
        childDir1 = new File(testDir1.getPath(), "/child1");
    }

    @AfterAll
    static void after()
    {
        assertTrue(deleteDirAndChildren(readDir));
    }

    @Test
    void readBytes()
    {
        if(PFiles.readBytes(empty).length > 0){
            fail();
        }
    }


    @Test
    void createDirectoryIfMissing()
    {
        assertTrue(PFiles.createDirectoryIfMissing(testDir1));
        assertTrue(PFiles.createDirectoryIfMissing(testDir1));
    }

    @Test
    void createDirectoryAndParentsIfMissing()
    {
        assertTrue(PFiles.createDirectoryAndParentsIfMissing(childDir1));
        assertTrue(PFiles.createDirectoryAndParentsIfMissing(childDir1));
        assertFalse(PFiles.createDirectoryAndParentsIfMissing(readDir.getPath()+"/era/er.txt"));
    }

    @Test
    void createDirectoriesAndFileIfMissing()
    {
        deleteDirAndChildrenSafe(testDir1);
        assertTrue(PFiles.createDirectoriesAndFileIfMissing(childDir1.getPath(), "hello.txt"));
        assertFalse(deleteDirAndChildrenSafe(testDir1));
    }

    @Test
    void createFileIfMissing()
    {
        File file = new File(readDir+"/et.txt");
        assertTrue(PFiles.createFileIfMissing(file));
        assertFalse(PFiles.createFileIfMissing(file));
        assertFalse(PFiles.createFileIfMissing(readDir+"/dir/et.txt"));
        file.delete();
    }

    @Test
    void copyFile()
    {
        assertTrue(PFiles.createDirectoriesAndFileIfMissing(readDir.getPath()+"/ee/", "eee.txt"));
        assertTrue(deleteDirAndChildren(new File(readDir.getPath()+"/ee/")));
    }

    @Test
    void clearFile()
    {
        assertTrue(PFiles.copyFile(test1, new File(readDir.getPath()+"/ee/rer.txt")));
        deleteDirAndChildren(new File(readDir.getPath()+"/ee"));
    }

    static boolean deleteDirAndChildrenSafe(File file)
    {
        return PFiles.deleteDirAndChildrenSafe(file) && PFiles.deleteDirAndChildrenSafe(file);
    }

    static boolean deleteDirAndChildren(File file)
    {
        return PFiles.deleteDirAndChildren(file) && PFiles.deleteDirAndChildren(file);
    }
}