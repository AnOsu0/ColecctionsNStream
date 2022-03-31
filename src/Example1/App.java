package Example1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class App {
    public static void main(String[] args) throws IOException {
        String fileName = "lorem.txt";
        Stream<String[]> stream = withoutEnters(fileName).stream();
        List<String> strings = stream.flatMap(Arrays::stream)
                .map(App::deleteLastChar)
                .toList();

        strings.stream().filter(App::sWords).forEach(System.out::println);

        long count = strings.stream().filter(App::fiveLetters).count();
        System.out.println(count);


    }
    private static List<String[]> withoutEnters (String fileName){
        List<String[]> result = new ArrayList<>();
        try(
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ){
            String nextLine = null;
            int i=0;
            while ((nextLine=reader.readLine() )!= null){
                if (!nextLine.equals("" +
                        "")){
                    result.add(nextLine.split(" "));
                    i++;}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String deleteLastChar (String s){
        char c = s.charAt(s.length() - 1);
        if (c == ',' || c == '.'){
            return s.substring(0, s.length() - 1);
        } else return s;
    }

    private static boolean sWords (String string){
        char c = string.charAt(0);
        char c1 = Character.toLowerCase(c);
        return c1 == 's';
    }

    private static boolean fiveLetters(String string){
        return string.length() == 5;
    }
}
