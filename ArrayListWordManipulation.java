import java.util.*;
public class ArrayListWordManipulation{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String s=sc.nextLine();
        s=s.replaceAll("[^a-zA-Z ]","");
        System.out.println(s);
        ArrayList<String> a=new ArrayList<>(Arrays.asList(s.trim().split(" ")));
        System.out.println("After seperating words :");
        System.out.println(a);
        while(true){
            System.out.println("=========================");
            System.out.println("1.Add new element into the arrayList\n2.Remove element from arraylist\n3.Exit");
            System.out.println("=========================");
            System.out.println("Enter your choice:");
            int ch=sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch(ch){
                case 1:
                System.out.println("Enter a new Element: ");
                String newElement=sc.nextLine();
                a.add(newElement);
                System.out.println("Adding a new element....");
                System.out.println(a);
                System.out.println();
                break;

                case 2:
                System.out.println("Enter the element to be removed: ");
                String removeElement=sc.nextLine();
                if(a.contains(removeElement)){
                a.remove(removeElement);
                System.out.println("Removing element....");
                System.out.println(a);
                System.out.println();
                }
                break;
                else{
                    System.out.println("Element not present in the array list....");
                }
                break;

                case 3:
                return;
            }
        }

    }

}
