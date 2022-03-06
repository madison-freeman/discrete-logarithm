/**
* Stanford University
* Cryptography I
*
* Week 5
* Discrete Log Program
*
*Your goal is to write a program to compute discrete log modulo a prime p.
*
*Let g be some element in Z * p and suppose you are given h in Z * p
such that h = g^x where 1 <= x <= 2^40
*
*Your goal is to find x. More precisely, the input to your program is (p, g, h)
*and the output is (x).

*/
class ProgrammingAssignment5 {
// Iterative Function to calculate (x ^ y)%p in O(log y)

static int powmod(int x, int y, int p) {
    int res = 1; // Initialize result
 
    x = x % p; // Update x if it is more than or
                // equal to p
 
    while (y > 0)
    {
        // For odd y, multiply x with result
        if ((y & 1)>0)
            res = (res*x) % p;
 
        // For even y
        y = y >> 1; // y = y / 2
        x = (x * x) % p;
    }
    return res;
}
 
// Function to calculate k for given a, b, m
static int discreteLogarithm(int a, int b, int m) {
 
    int n = (int) (Math.sqrt (m) + 1);
 
    int[] value = new int[m];
 
    // Store all values of a^(n * i) in L.H.S
    for (int i = n; i >= 1; --i)
        value[ powmod (a, i * n, m) ] = i;
 
    for (int j = 0; j < n; ++j)
    {
        // calculate (a ^ j) * b and check
        // for collision
        int cur = (powmod (a, j, m) * b) % m;
 
        // if collision occurs then LHS = RHS
        if (value[cur]>0)
        {
            int ans = value[cur] * n - j;
            // Check whether result lies below m or not
            if (ans < m)
                return ans;
        }
    }
    return -1;
}
 
public static void main(String[] args)
{
    int a = 2, b = 3, m = 5;
    System.out.println(discreteLogarithm(a, b, m));
 
    a = 3;
    b = 7;
    m = 11;
    System.out.println(discreteLogarithm(a, b, m));
}
}