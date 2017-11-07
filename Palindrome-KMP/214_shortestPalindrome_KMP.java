/*214. Shortest Palindrome
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

 For example:

 Given "aacecaaa", return "aaacecaaa".

 Given "abcd", return "dcbabcd".

 KMP substring search:
 https://www.youtube.com/watch?v=GTJr8OvyEVQ : subfix is prefix in the pattern..
  _   _ mismatch
 | | | ||
 abcdabca   since the letter not matching y's prefix is abc, so we can start next match from abcd's d
 KMP PREFIX ARRAY:  
 abcdabca  aabaabaaa  -- the last a go back 3->1 match s[1]='a', so 1+1=2.
 00001231  010123452  -- value stores here means where to start next if we got a mismatch
 */
public String shortestPalindrome(String s) {
    if(s==null||s.length()==0) return ""; 
    
    int n=s.length();
    String rev = new StringBuilder(s).reverse().toString();
    s += "#"+rev;
    int[] kmp = new int[s.length()];
    for(int i=1; i< s.length(); i++) {
        int t=kmp[i-1];
        while(t>0 && s.charAt(i)!=s.charAt(t)){
            t=kmp[t-1];
        }
        if(s.charAt(i)==s.charAt(t)) t++;
        kmp[i]=t;
    }

    return rev.substring(0,n-kmp[s.length()-1])+s.substring(0,n);
}


// naive solution(c++):
string shortestPalindrome(string s) {
    int n=s.size();
    string rev(s);
    reverse(rev.begin(), rev.end());
    int j=0;
    for(int i=0; i< n; i++) {
        // c++ reload '==' for string object, which is lhs.compare(rhs)==0 
        if(s.substr(0, n-i)==rev.substr(i)) return rev.substr(0,i)+s;
    }
    return "";
}
