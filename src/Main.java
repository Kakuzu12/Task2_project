import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class Main
{
    public static void main(String[] args) {
        try(BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in))){
            System.out.println("Enter number of elements:");
            int numberOfElements = Integer.parseInt(bufferedReader.readLine());
            Hashtable<String, Integer> dict = dictionaryConstruction(numberOfElements);
            List<String> answer = writeToFileAndRecord(dict);
            for(int i = 0;i < answer.size()-1;i++){
                System.out.println("Record is equal to: " + answer.get(i) + " " + answer.get(answer.size()-1));
            }
        }
        catch(IOException e){
            e.getMessage();
        }
        catch(NumberFormatException e){
            System.out.println("Incorrect input! Only integers are allowed!");
        }
    }
    public static Hashtable<String, Integer> dictionaryConstruction (int numberOfElements) {
        try(BufferedReader Reader = new BufferedReader (new InputStreamReader(System.in))){
            Hashtable<String, Integer> dict = new Hashtable<>();
            for(int j=1;j <= numberOfElements;j++){
                String[] words = Reader.readLine().split(" ");
                dict.merge(words[0], Integer.parseInt(words[1]), Integer::sum);
            }
            return dict;
        }
        catch(IOException e){
            e.getMessage();
            return new Hashtable<>();
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Enter data and number of push ups separated by space");
            return new Hashtable<>();
        }
    }
    public static List<String> writeToFileAndRecord (Hashtable<String, Integer> dict) {
        Set<String> years = dict.keySet();
        try(FileWriter outputFile = new FileWriter("output.txt")){
            String newLine = System.getProperty("line.separator");
            int maxValue = 0;
            List<String> yearsMax = new ArrayList<>();
            for(String key : years) {
                outputFile.write(key + " " + dict.get(key) + newLine);
                if (dict.get(key) > maxValue){
                    maxValue = dict.get(key);
                    yearsMax.clear();
                    yearsMax.add(key);
                }
                else if (dict.get(key) == maxValue){
                    yearsMax.add(key);
                }
            }
            yearsMax.add(String.valueOf(maxValue));
            return yearsMax;
        }

        catch(IOException e){
            e.getMessage();
            return new ArrayList<>();
        }

    }
}

