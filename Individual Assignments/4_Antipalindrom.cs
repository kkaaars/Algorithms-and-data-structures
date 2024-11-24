using System;
using System.Collections.Generic;

public class Program
{
    public static void Main()
    {
        var input = Console.ReadLine().Split(' ');
        int l = int.Parse(input[0]);
        int c = int.Parse(input[1]);
        char[] s = Console.ReadLine().ToCharArray();
        if (l < 2 * c)
        {
            Console.WriteLine("Epic fail");
            return;
        }
        int[] vector = new int[26];
        List<char> antipalindrom = new List<char>();
        int index, counter = 0;
        for (int i = 0; i < l; i++)
        {
            index = s[i] - 'a';
            if (vector[index] != c)
            {
                vector[index]++;
                counter++;
            }
        }
        if (counter < 2 * c)
        {
            Console.WriteLine("Epic fail");
            return;
        }
        index = 0;
        for (int i = 0; i < c; i++)
        {
            while (vector[index] == 0)
            {
                index++;
            }
            antipalindrom.Add((char)(index + 'a'));
            vector[index]--;
        }
        index = vector.Length - 1;
        for (int i = 0; i < c; i++)
        {
            while (vector[index] == 0)
            {
                index--;
            }
            antipalindrom.Add((char)(index + 'a'));
            vector[index]--;
        }
        foreach (char a in antipalindrom)
        {
            Console.Write(a);
        }
    }
}