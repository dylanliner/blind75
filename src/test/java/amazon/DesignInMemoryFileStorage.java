package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class DesignInMemoryFileStorage {


    class FileSystem {

        class SystemNode {
            StringBuilder contentStringBuilder = new StringBuilder();
            SortedMap<String, SystemNode> children = new TreeMap<>();
            String name;

            public List<String> getNodeContents() {
                var list = new ArrayList<String>();

                if (contentStringBuilder.length() > 0) {
                    list.add(name);
                } else {
                    list.addAll(children.keySet());
                }
                return list;
            }


            public void append(String content) {
                contentStringBuilder.append(content);
            }

            public String read() {
                return contentStringBuilder.toString();
            }

            public SystemNode(String name) {
                this.name = name;
            }
        }

        SystemNode root = new SystemNode("");

        private SystemNode findSystemNode(String path) {
            String[] paths = path.split("/");
            var cur = root;
            for (String currentPath : paths) {
                if (currentPath.length() == 0) {
                    continue;
                }
                cur.children.computeIfAbsent(currentPath, s -> new SystemNode(currentPath));
                cur = cur.children.get(currentPath);

            }
            return cur;
        }

        public FileSystem() {
        }

        public List<String> ls(String path) {
            return findSystemNode(path).getNodeContents();
        }

        public void mkdir(String path) {
            findSystemNode(path);
        }

        public void addContentToFile(String filePath, String content) {
            findSystemNode(filePath).append(content);

        }

        public String readContentFromFile(String filePath) {
            return findSystemNode(filePath).read();
        }
    }

    @Test
    public void test2() {
        var array = "/".split("/", 3);
        assertEquals(array[0], "");
    }

    @Test
    public void test() {
        FileSystem fileSystem = new FileSystem();
        fileSystem.ls("/");                         // return []
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        fileSystem.ls("/");                         // return ["a"]
        fileSystem.ls("/a/b");                         // return ["a"]
        fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
    }
}
