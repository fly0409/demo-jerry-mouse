import java.util.Arrays;
import java.util.regex.Pattern;

public class Test {
//    public static void main(String[] args) {
//        System.out.println("test");
//
//        Pattern QUERY_SPLIT = Pattern.compile("\\&");
//        String testString = "aaa&bb";
//        String[] ss = QUERY_SPLIT.split(testString);
//        Arrays.stream(ss).forEach(System.out::println);
//
//
//    }

    public static void main(String[]args){

        //Get the jvm heap size.
        long heapSize = Runtime.getRuntime().totalMemory();

        //Print the jvm heap size.
        System.out.println("Heap Size = " + heapSize);
    }
}
