import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileSystem {

    class Directory{
        HashMap<String,Directory> directoryHashMap = new HashMap<>();
        HashMap<String,String> files  = new HashMap<>();
    }

    Directory root;
    public FileSystem() {

        root = new Directory();
    }

    public List<String> ls(String path) {

        List<String> files = new ArrayList<>();

        Directory t = root;

        if(!path.equals("/")){
            String [] d = path.split("/");
            for(int i =1 ; i < d.length;i++){
                t = t.directoryHashMap.get(d[i]);
            }

            if(t.files.containsKey(d[d.length-1])){
                files.add(d[d.length-1]);
                return files;

            }else {
                t = t.directoryHashMap.get(d[d.length-1]);
            }
        }

        files.addAll(new ArrayList<> (t.directoryHashMap.keySet()));
        files.addAll(new ArrayList<>(t.files.keySet()));
        Collections.sort(files);
        return files;

    }

    public void mkdir(String path) {
        Directory t =root;

        String [] d = path.split("/");

        for(int i = 1; i < d.length;i++){
            if(!t.directoryHashMap.containsKey(d[i])){
                t.directoryHashMap.put(d[i],new Directory());
            }

            t = t.directoryHashMap.get(d[i]);
        }
    }

    public void addContentToFile(String filePath, String content) {

        Directory t= root;
        String [] d = filePath.split("/");
        for(int i = 1; i < d.length-1;i++){
            t= t.directoryHashMap.get(d[i]);
        }
        t.files.put(d[d.length-1],t.files.getOrDefault(d[d.length-1],"") + content);
    }

    public String readContentFromFile(String filePath) {

        Directory t =root;
        String [] d = filePath.split("/");
        for(int i =1 ; i < d.length-1; i++ ){
            t = t.directoryHashMap.get(d[i]);
        }

        return t.files.get(d[d.length-1]);
    }

}
