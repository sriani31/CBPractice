import java.util.HashMap;

public class MyMaps {

    public static void main(String[] args) {

        HashMap empIds = new HashMap();

        empIds.put("animesh", 1);
        empIds.put(1,2);
        empIds.put("1",3);

        System.out.println(empIds.containsKey(1));

        System.out.println(empIds);
        System.out.println(empIds.get("1"));

        System.out.println(empIds.put("1",4));

        System.out.println(empIds.containsValue(4));
        //System.out.println(empIds.re);

        System.out.println(empIds.getOrDefault("5",empIds.put("5",1)));

        System.out.println(empIds);


    }
}
