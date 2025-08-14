import java.util.*;
public class frequencyCounter{
    public static void main(String[] args){
        String s="  Hello Hello World ?? new ";
        s=s.replaceAll("[^a-zA-Z ]","");
        ArrayList<String> a=new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        Collections.sort(a);
        HashMap<String, Integer> wordCount=new HashMap<>();
        for(String word:a){
            wordCount.put(word,wordCount.getOrDefault(word,0)+1);
        }
        for(String word:wordCount.keySet()){
            System.out.println(word+" -> "+wordCount.get(word));
        }
    }
}