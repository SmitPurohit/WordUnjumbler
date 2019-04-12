import java.io.*;

import java.util.ArrayList;

import java.util.Scanner;
public class Dejumbler
{
   static ArrayList<String> listWords = new ArrayList<String>();
   public static void main(String args[])
   {
      Scanner input = new Scanner(System.in);
       System.out.print("Enter jumbled word: ");
      
      String word = input.nextLine(); 
      fillList();
      System.out.println(solve(word.toLowerCase()));
   }
   //fills the arraylist of all words in english dictionary --> maybe only add words that have a first letter
   public static void fillList()
   {
      try{
         BufferedReader br = new BufferedReader(new FileReader("words_alpha.txt"));
         String line;
         while((line = br.readLine()) != null) {
            listWords.add(line);
         }
         
      }
      catch(Exception e) {System.out.print("Error reading file");}
   }
   
   //This is the solve method, change the method used if antoher way to solve is found
   
   public static String solve(String input) //try a unique id to each word -- maybe multiplication
   {
      boolean done = false;
      String jumble = "";
      for(int k = 0; k < listWords.size(); k++)
      {
         
         if(!done)
         {
            jumble = jumble(input);
            System.out.println(jumble);
         }
         else
            break;
         for(String str: listWords)
         {
            if(jumble.equals(str))
               done = true;
               
         }
      }
            
      return jumble;
   }
   
   public static String jumble(String in) //try random every letter -- longer but would eventually work
   {
      
      int length = in.length();
      int[] exclude = new int[length];
      boolean ex = false;
      String out = "";
      ArrayList<String> array = new ArrayList<String>();
      
      //convert String into String array to allow for easier manipulation
      for(int k = 0; k < length; k++)
      {
         if(k==length-1)
            array.add(in.substring(k));
         else
            array.add(in.substring(k,k+1));
         
      }
      for(int i = 0; i < length; i++)
      {
         int rand = (int)(Math.random()*array.size());
         String letter = array.get(rand);
         array.remove(rand);
         out += letter;
      }
         
      return out;
   }
   public static String getFirstLetter(String input)
   {
      return input.substring(0,1);
   }         
               
}