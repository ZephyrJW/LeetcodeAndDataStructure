/*198.House Robber
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */
public int rob(int[] nums) {
    if(nums==null || nums.length==0) return 0;

    int n=nums.length;
    int[] dp = new int[n];
    dp[0]=nums[0]; dp[1]=Math.max(dp[0],nums[1]);

    for(int i=2; i< n; i++) {
        dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1]);
        // max(rob this house, not rob this house);
    }
    return dp[n-1];
}

/*213.House Robber II
 Same logic. This time, all houses at this place are arranged in a circle. 
 */
private int rob(int[] nums, int lo, int hi) {
    int include = 0, exclude = 0;
    for(int i=lo; i< hi; i++){
        int tmp1 = include, tmp2 = exclude;
        include = tmp2+nums[i];
        exclude = Math.max(exclude, tmp1);
    }
    return Math.max(include, exclude);
}

public int rob(int[] nums) {
    if(nums.length==1) return nums[0];
    // whether steal the first house or not helps break the cycle
    return Math.max(rob(nums, 0, nums.length-2), rob(nums, 1, nums.length-1));
}

// two-pass solution, easy to understand
public int rob(int[] nums) {
    if(nums.length==0) return 0;
    if(nums.length==1) return nums[0];

    int n=nums.length;
    int[] s1 = new int[n];
    int[] s2 = new int[n];

    //first pass, assuming house #0 is stolen, hence last house #n-1 can't be stolen as well as house #1
    s1[0]=nums[0]; s1[1]=nums[0];
    for(int i=2; i< n-1; i++) { // note here i< n-1
        s1[i] = Math.max(s1[i-2]+nums[i], s1[i-1]);
    }

    //second pass, assuming house #0 is not stolen. Starting from house #1
    s2[1] = nums[1]; s2[2] = Math.max(s2[1], nums[2]); 
    for(int i=3; i< n; i++){
        s2[i] = Math.max(s2[i-2]+nums[i], s2[i-1]);
    }

    return Math.max(s1[n-2], s2[n-1]);
}

/*337.House Robber III
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

     3(!)
    / \
   2   3
    \   \ 
     3(! 1(!)
Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
https://discuss.leetcode.com/topic/39834/step-by-step-tackling-of-the-problem
*/
public int rob(TreeNode root) {

}





