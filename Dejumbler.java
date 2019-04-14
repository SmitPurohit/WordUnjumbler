import java.io.*;

import java.util.ArrayList;

import java.util.Scanner;
public class Dejumbler
{
   static ArrayList<String> listWords = new ArrayList<String>(); //array of all words in English
   static ArrayList<String> array = new ArrayList<String>(); //array of letters in input word
   static ArrayList<String> exclude = new ArrayList<String>(); //array of already created combinations from jumble
   static int count = 0;
   static  String word;
   public static void main(String args[])
   {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter jumbled word: ");
      
      word = input.nextLine(); 
      
      //convert String into String array to allow for easier manipulation
      int length = word.length();
      for(int k = 0; k < length; k++)
      {
         if(k==length-1)
            array.add(word.substring(k));
         else
            array.add(word.substring(k,k+1));
         
      }
      
      fillList();
      
      System.out.println(solve(word.toLowerCase()) + "\nSolved in: " + count +" iterations");
   }
   
   
   //fills the arraylist of words from the english dictionary whose first letter is in the array listWords and if the length is the same
   //in addition it only adds to the array if the word and the input have the same amount of vowels
   //all conditions are to reduce the amount of words in the list and therefore less time to iterate through it
   
   //as far as I can tell fillList takes significantly less time so complexity here would not affect time as much compared to jumble
   public static void fillList()
   {
      try{
         BufferedReader br = new BufferedReader(new FileReader("words_alpha.txt"));
         String line;
         while((line = br.readLine()) != null) {
            for(String letter: array)
            {
               if(getFirstLetter(line).equals(letter)&&word.length()==line.length()&&countVowels(line)==countVowels(word))
               {
                  listWords.add(line);
                  break;
               }
            }
            
         }
         
         
      }
      catch(Exception e) {System.out.print("Error reading file");}
   }
   
   //This is the solve method, change the method used if another way to solve is found
   //as of right now a jumble method is called
   //Solve takes the input, jumbles it, and checks if the resulting string is in the list of all words
   
   public static String solve(String input) //try a unique id to each word -- maybe multiplication
   {
      boolean done = false;
      String jumble = "";
      while(!done)
      {
         count++;
         jumble = jumble(input);
         System.out.println(jumble);
         for(String str: listWords)
         {
            if(jumble.equals(str))
               done = true;
               
         }
      }
            
      return jumble;
   }
   
   //randomly jumbles the word --> does not check for previously created jumbles (created more lag when check created)
   public static String jumble(String in) 
   {
      
      int length = in.length();
      
      boolean ex = false;
      String out = "";
      
      //jumble word
      for(int i = 0; i < length; i++)
      {
         int rand = (int)(Math.random()*array.size());
         
         String letter = array.get(rand);
         array.remove(rand);
         out += letter;
      }
       //re-adds all letters back to the array
      for(int k = 0; k < length; k++)
      {
         if(k==length-1)
            array.add(in.substring(k));
         else
            array.add(in.substring(k,k+1));
         
      }
     
      return out;
   }
   
   
   public static String getFirstLetter(String input)
   {
      return input.substring(0,1);
   }  
   
   public static int countVowels(String input)
   {
      int count = 0;
      for(int k = 0; k < input.length(); k++)
      {
         String sub = input.substring(k,k+1);
         if(sub.equals("a")||sub.equals("e")||sub.equals("i")||sub.equals("o")||sub.equals("u"))
            count++;
      }
      return count;
   }      
               
}