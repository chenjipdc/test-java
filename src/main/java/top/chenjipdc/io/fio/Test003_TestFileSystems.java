package top.chenjipdc.io.fio;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/30 2:34 下午
 */
public class Test003_TestFileSystems {
    static void show(String id, Object o) {
        System.out.println(id + ": " + o);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        FileSystem fsys = FileSystems.getDefault();
        for (FileStore fs : fsys.getFileStores())
            show("File Store",
                    fs);
        for (Path rd : fsys.getRootDirectories())
            show("Root Directory",
                    rd);
        show("Separator",
                fsys.getSeparator());
        show("UserPrincipalLookupService",
                fsys.getUserPrincipalLookupService());
        show("isOpen",
                fsys.isOpen());
        show("isReadOnly",
                fsys.isReadOnly());
        show("FileSystemProvider",
                fsys.provider());
        show("File Attribute Views",
                fsys.supportedFileAttributeViews());
    }
}

class TestPathWatcherDelete {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("/tmp/test");

        WatchService watchService = FileSystems.getDefault()
                .newWatchService();

        path.register(watchService,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.OVERFLOW);

        WatchKey take = watchService.take();

        while (true) {
            for (WatchEvent<?> pollEvent : take.pollEvents()) {
                System.out.println("evt.context(): " + pollEvent.context() +
                        "\nevt.count(): " + pollEvent.count() +
                        "\nevt.kind(): " + pollEvent.kind());
            }
            take.reset();
            Thread.sleep(10);
        }
    }
}


class TestFileMatcher {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/tmp/test");

        // glob或者regex
        PathMatcher pathMatcher = FileSystems.getDefault()
                .getPathMatcher("glob:**/*.txt");

        // 匹配文件类型
        Files.walk(path)
                .filter(pathMatcher::matches)
                .forEach(System.out::println);

        PathMatcher pathMatcher1 = FileSystems.getDefault()
                .getPathMatcher("glob:*.txt");
        // 匹配文件名
        Files.walk(path)
                .map(Path::getFileName)
                .filter(pathMatcher1::matches)
                .forEach(System.out::println);
    }
}
