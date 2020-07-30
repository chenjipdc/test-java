package top.chenjipdc.io.fio;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/30 2:34 下午
 */
public class Test002_Files {
    static Path base = Paths.get("")
            .toAbsolutePath()
            .normalize();

    static void show(int id, Path result) {
        if (result.isAbsolute())
            System.out.println("(" + id + ")r " + base.relativize(result));
        else
            System.out.println("(" + id + ") " + result);
        try {
            System.out.println("RealPath: " + result.toRealPath());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.out.println(base);

        Path p = Paths.get("src/main/java/top/chenjipdc/io/fio/Test002_Files.java");
        show(1,
                p);

        Path convoluted = p.getParent()
                .getParent()
                .resolve("fio")
                .resolve("..")
                .resolve(p.getParent()
                        .getFileName());
        show(2,
                convoluted);

        show(3,
                convoluted.normalize());

        Path p2 = Paths.get("..",
                "..");
        show(4,
                p2);
        show(5,
                p2.normalize());
        show(6,
                p2.toAbsolutePath()
                        .normalize());

        Path p3 = Paths.get(".")
                .toAbsolutePath();
        Path p4 = p3.resolve(p2);
        show(7,
                p4);
        show(8,
                p4.normalize());

        Path p5 = Paths.get("")
                .toAbsolutePath();
        show(9,
                p5);
        show(10,
                p5.resolveSibling("test-java"));
        show(11,
                Paths.get("nonexistent"));
    }
}

class TestFilesWalk {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/test");

        List<Path> collect = Files.walk(path,
                FileVisitOption.FOLLOW_LINKS)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("===============");

        Files.walkFileTree(path,
                new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        System.out.println("pre dir: " + dir);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println("visit: " + file);
                        // 做点事情可以删除文件
                        // Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        System.out.println("visit failed: " + file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        System.out.println("post dir: " + dir);
                        // 做点事情可以删除文件夹
                        // Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
    }
}


class TestFileRead {
    public static void main(String[] args) throws IOException {
        // 一次性读完
        Path path = Paths.get("/tmp/test/1.txt");
        Files.readAllLines(path)
                .stream()
                .forEach(System.out::println);

        // 当文件很大时，一点一点读取
        Files.lines(path)
                .forEach(System.out::println);
    }
}

class TestFileWrite {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/test/2.txt");
        Files.write(path,
                "1 \n2 \n3 \n4 \n5 \n6 \n7 \n8 \n9 \n0\n".getBytes(),
                StandardOpenOption.APPEND);
        Files.readAllLines(path)
                .stream()
                .forEach(System.out::println);
    }
}

class TestReadAndWrite {
    public static void main(String[] args) throws IOException {
        Path read = Paths.get("/tmp/test/2.txt");
        Path write = Paths.get("/tmp/test/3.txt");
        try (
                PrintWriter printWriter = new PrintWriter(write.toFile());
        ) {
            Files.lines(read)
                    .forEach(printWriter::println);
        } catch (Exception e) {
            throw e;
        }
        Files.readAllLines(write)
                .stream()
                .forEach(System.out::println);
    }
}