using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static List<int> suf;
    static string str;

    static List<int> SuffixArray(List<int> s)
    {
        s.Add('#');
        int n = s.Count, cnt = 0, cls = 0;
        List<int> c = new List<int>(new int[n]);
        List<int> p = new List<int>(new int[n]);

        var t = new SortedDictionary<int, List<int>>();
        for (int i = 0; i < n; i++)
        {
            if (!t.ContainsKey(s[i])) t[s[i]] = new List<int>();
            t[s[i]].Add(i);
        }

        foreach (var x in t)
        {
            foreach (int u in x.Value)
            {
                c[u] = cls;
                p[cnt++] = u;
            }
            cls++;
        }

        for (int l = 1; cls < n; l++)
        {
            List<List<int>> a = new List<List<int>>();
            for (int i = 0; i < cls; i++)
            {
                a.Add(new List<int>());
            }
            List<int> _c = new List<int>(new int[n]);
            int d = (1 << l) / 2;
            int _cls = cnt = 0;

            for (int i = 0; i < n; i++)
            {
                int k = (p[i] - d + n) % n;
                a[c[k]].Add(k);
            }

            for (int i = 0; i < cls; i++)
            {
                for (int j = 0; j < a[i].Count; j++)
                {
                    if (j == 0 || c[(a[i][j] + d) % n] != c[(a[i][j - 1] + d) % n])
                        _cls++;
                    _c[a[i][j]] = _cls - 1;
                    p[cnt++] = a[i][j];
                }
            }

            c = _c;
            cls = _cls;
        }

        return p.GetRange(1, p.Count - 1);
    }

    public static void Main()
    {
        str = Console.ReadLine();
        List<int> s = str.Select(ch => (int)ch).ToList();
        suf = SuffixArray(s);
        Console.WriteLine(suf.Count);
        foreach (var i in suf)
        {
            Console.Write((i + 1) + " ");
        }
    }
}