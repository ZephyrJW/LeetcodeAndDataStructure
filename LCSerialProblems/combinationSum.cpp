/*39.Combination Sum
Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [2, 3, 6, 7] and target 7, 
A solution set is: 
[
  [7],
  [2, 2, 3]
]
 */
vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
    vector<int> ans;
    vector<vector<int>> res;
    if(candidates.size()==0) return res;

	sort(candidates.begin(), candidates.end());
    dfs(candidates, 0, target, ans, res);
    return res; 
}

void dfs(vector<int>& nums, int index, int n, vector<int>& ans, vector<vector<int>>& res){
	if(n<0) return;
	if(n==0) res.push_back(ans);
	else{
		for(int i=index; i< nums.size(); i++){
			ans.push_back(nums[i]);
			dfs(nums, i, n-nums[i], ans, res);
			ans.pop_back();
		}
	}
}

/*40.Combination Sum II
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, 
A solution set is: 
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
 */
vector<vector<int>> combinationSum2(vector<int>& candidates, int target){
	vector<vector<int>> res;
	vector<int> ans;
	if(candidates.size()==0) return res;

	sort(candidates.begin(), candidates.end());
	dfs(candidates, 0, target, ans, res);
	return res;
}

void dfs(vector<int>& nums, int index, int n, vector<int>& ans, vector<vector<int>>& res){
	if(n<0) return;
	if(n==0) res.push_back(ans);
	else{
		for(int i=index; i< nums.size(); i++){
			if(i==index || nums[i]!=nums[i-1]){
				ans.push_back(nums[i]);
				dfs(nums, i+1, n-nums[i], ans, res);
				ans.pop_back();
			}
		}
	}
}

/*216.Combination Sum III
Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Example 1:
Input: k = 3, n = 7
Output:
[[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output:
[[1,2,6], [1,3,5], [2,3,4]]
 */
vector<vector<int>> combinationSum3(int k, int n){
	vector<vector<int>> res;
	vector<int> ans;

	dfs(k, n, 1, ans, res);
	return res;
}

void dfs(int k, int n, int index, vector<int>& ans, vector<vector<int>>& res) {
	if(n==0&&k==0){
		res.push_back(ans);
		return;
	}

	if(k==0) return;

	for(int i=index; i< 10; i++){
		ans.push_back(i);
		dfs(k-1, n-i, i+1, ans, res);
		ans.pop_back();
	}
}
