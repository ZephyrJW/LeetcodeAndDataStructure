/* 166. Fraction to Recurring Decimal
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,
    1/7 = "0.(142857)"
    Given numerator = 1, denominator = 2, return "0.5".
    Given numerator = 2, denominator = 1, return "2".
    Given numerator = 2, denominator = 3, return "0.(6)".
*/
public String fractionToDecimal(int numerator, int denominator) {
    if(numerator==0) return "0";
    StringBuilder sb = new StringBuilder();

    if(numerator>0 ^ denominator>0) sb.append("-"); // fancy way
    // convert to long because abs(Integer.MIN_VALUE) overflows
    long n = Math.abs(Long.valueOf(numerator));
    long d = Math.abs(Long.valueOf(denominator));
    sb.append(n/d);
    n %= d;
    if(n==0) return sb.toString();

    sb.append(".");
    HashMap<Long, Integer> map = new HashMap<>();
    while(n!=0) {
        if(map.containsKey(n)){
            sb.insert(map.get(n),"(");
            sb.append(")");
            break;
        }

        map.put(n, sb.length());
        n *= 10;
        sb.append(n/d);
        n %= d;
    }
    return sb.toString();
}

/* 50.Pow(x,n)
Implement pow(x, n).
 */
public double myPow(double x, int n) {
    if(n==0) return (double)1;
    
    if(n==Integer.MIN_VALUE){
        x = x*x;
        n = n/2;
    }

    if(n< 0) {
        x = 1/x;
        n = -n;
    }

    return n%2==0? myPow(x*x, n/2): x*myPow(x*x, n/2);
}

/* 69.Sqrt(x)
Implement int sqrt(int x) to compute and return the square root of x.
 */
public int mySqrt(int x) {
    if(x==0) return 0;
    if(x<0) return Integer.MIN_VALUE;

    int lo=1, hi=x;
    while(lo<=hi) {
       int mid = lo+(hi-lo)/2;
       if(mid> x/mid){
            hi = mid-1; 
       }else {
            if((mid+1)> x/(mid+1)) return mid;
            lo = mid+1;
       }
    }
}
// Using Newton's method
public int mySqrt(int x) {
    long res = x;
    while(res*res> x) {
        res = (res+x/res)/2;
    }
    return (int)res;
}


/* 7. Reverse Integer
 */
public int reverse(int x) {
    int sign = 1, res=0;
    if(x<0){
        sign = -1;
        x = -x;
    }

    while(x!=0){
        int tmp = res*10+x%10;
        // check overflows could be: 
        // if(tmp/10!=res) return 0;
        if((tmp-x%10)/10!=res) return 0;
        res = tmp;
        x /= 10;
    }

    return sign*res;
}

/*172.Factorial Trailing Zeroes
 Given an integer n, return the number of trailing zeroes in n!.
 Find 5 in the sequence, however, 25 has 5*5, two 5s in it..
 */
public int trailingZeroes(int n) {
    if(n==0) return 0;
    return n/5+trailingZeroes(n/5);
}


/*204.Count Primes
 Count the number of prime numbers less than a non-negative number, n.
 */
public int countPrimes(int n) {
    int count = 0;
    boolean[] mark = new boolean[n];

    for(int i=2; i< n; i++) {
        if(!mark[i]){
            count++;
            for(int j=2; i*j< n; j++) mark[i*j] = true;
        }
    }
    return count;
}

/*264. Ugly Number II
 Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 */
public int nthUglyNumber(int n) {
    int[] ugly = new int[n];
    ugly[0] = 1;
    int p2=0, p3=0, p5=0;
    for(int i=1; i< n; i++) {
        ugly[i] = Math.min(Math.min(ugly[p2]*2, ugly[p3]*3),ugly[p5]*5);

        if(ugly[i]==ugly[p2]*2) p2++;
        if(ugly[i]==ugly[p3]*3) p3++;
        if(ugly[i]==ugly[p5]*5) p5++;
    }
    return ugly[n-1];
}





















