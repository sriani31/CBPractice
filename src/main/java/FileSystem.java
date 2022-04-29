import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileSystem {
    class Dir {
        HashMap<String,Dir> directory = new HashMap<>();
        HashMap<String, String> files = new HashMap<>();
    }
    Dir root;

    public FileSystem(){
        root = new Dir();
    }

    public List<String> ls(String path) {

        Dir t = root;

        List<String> files = new ArrayList<>();

        if(!path.equals("/")){
            String [] d= path.split("/");
            for (int i = 0 ; i < d.length;i++){
                t = t.directory.get(d[i]);
            }
            if(t.files.containsKey(d[d.length-1])){
               files.add(d[d.length-1]);
               return files;

            } else {
                t = t.directory.get(d[d.length-1]);
            }
        }
        files.addAll(new ArrayList<>(t.directory.keySet()));
        files.addAll(new ArrayList<>(t.files.keySet()));
       return new ArrayList<>();

    }

    public void mkdir(String path) {

        Dir t = root;
        String [] d = path.split("/");

        for(int i = 0 ; i < d.length ; i++){

            if(!t.directory.containsKey(d[i])){
                t.directory.put(d[i] , new Dir());
            }
            t = t.directory.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {

    }

    public String readContentFromFile(String filePath) {

        return "NA";
    }
}
