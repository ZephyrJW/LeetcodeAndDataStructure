/* 
164. Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

// 1. Comparision sort, O(nlgn), trivial
public int maximumGap(int[] nums) {
	if(nums==null || nums.length< 2) return 0;

	Arrays.sort(nums);
	int res = 0;
	for(int i=0; i< nums.length-1; i++) {
		res = Math.max(res, nums[i+1]-nums[i]);
	}

	return res;
}

// 2. Radix Sort
public int maximumGap(int[] nums) {
	if(nums==null || nums.length< 2) return 0;

	int max = nums[0];
	for(int num: nums) max = Math.max(max, num);

	int exp = 1, radix = 10;
	int[] aux = new int[nums.length];

	while(max/exp > 0) {
		int[] count = new int[radix];

		for(int i=0; i< nums.length; i++) {
			count[(nums[i]/exp) % radix]++;
		}

		for(int i=1; i< count.length; i++) {
			count[i] += count[i-1];
		}

		// for(int i=0; i< nums.length; i++) {
		for(int i=nums.length-1; i>=0; i--) {
			aux[--count[(nums[i]/exp)%10]] = nums[i];
		}

		for(int i=0; i< nums.length; i++) {
			nums[i] = aux[i];
		}

		exp*=10;
	}

	int res = 0;
	for(int i=0; i< nums.length-1; i++) {
		res = Math.max(res, nums[i+1]-nums[i]);
	}
	return res;
}

// 3. Bucket Sort
// proof: https://leetcode.com/problems/maximum-gap/solution/
class bucket {
	boolean used;
	int max, min;
	public bucket() {
		used = false;
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
	}
}

public int maximumGap(int[] nums) {
	if(nums==null || nums.length<2) return 0;

	// int maxt = Collections.max(nums);
	// int mint = Collections.min(nums);
	int maxt = nums[0], mint = nums[0];
	for(int num: nums) {
		maxt = Math.max(maxt, num);
		mint = Math.min(mint, num);
	}

	int bucketNumber = Math.max(1, (maxt-mint)/(nums.length-1));
	int numBucket = (maxt-mint)/bucketNumber+1;
	bucket[] container = new bucket[numBucket];

	for(int num: nums) {
		int index = (num-mint)/bucketNumber;
		container[index] = new bucket();
		container[index].max = Math.max(num, container[index].max);
		container[index].min = Math.min(num, container[index].min);
	}

	int prevMax = mint, res = 0;
	for(int i=0; i< numBucket; i++) {
		if(container[i]==null) continue;

		res = Math.max(res, container[i].min - prevMax);
		prevMax = bucket.max;
	}

	return res;
}




















