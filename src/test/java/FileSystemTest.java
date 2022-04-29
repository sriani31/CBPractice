import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemTest {


    @Test
    public void test_mkdir(){
        FileSystem fileSystem  = new FileSystem();

        fileSystem.mkdir("/a/b/c");
        fileSystem.mkdir("/a/b/f");
    }
}