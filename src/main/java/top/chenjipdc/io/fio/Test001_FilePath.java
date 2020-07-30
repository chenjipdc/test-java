package top.chenjipdc.io.fio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/30 2:13 下午
 */
public class Test001_FilePath {
    static void println(String id, Object p){
        System.out.println(id + ": " + p);
    }

    static void info(Path p){
        println("toString", p);
        println("Exists", Files.exists(p));
        println("RegularFile", Files.isRegularFile(p));
        println("Directory", Files.isDirectory(p));
        println("Absolute", p.isAbsolute());
        println("FileName", p.getFileName());
        println("Parent", p.getParent());
        println("Root", p.getRoot());
        System.out.println("******************");
    }

    public static void main(String[] args) {
        Path path = Paths.get("Test001_FilePath.java");
        info(path);
        Path path1 = path.toAbsolutePath();
        info(path1);
        info(path1.getParent());

        try {
            info(path.toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(path.toUri());
    }
}
