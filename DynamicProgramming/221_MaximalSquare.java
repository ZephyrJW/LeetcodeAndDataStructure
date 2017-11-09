/*
 Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 For example, given the following matrix:

 1 0 1 0 0
 1 0 I I 1
 1 1 I I 1
 1 0 0 1 0
 Return 4.
 */

public int maximalSquare(char[][] matrix) {
    if(matrix==null || matrix.length==0 || matrix[0].length==0) return 0;
    
    int m=matrix.length, n=matrix[0].length, res=0;
    int[][] dp = new int[m+1][n+1];

    for(int i=1; i< m+1; i++){
        for(int j=1; j< n+1; j++){
            if(matrix[i-1][j-1]=='1'){
                dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1],[j-1])+1;
                res = Math.max(res, dp[i][j]);
            }
        }
    }

    return res*res;
}

