
Leetcode article:
https://leetcode.com/articles/recursive-approach-segment-trees-range-sum-queries-lazy-propagation/#range-sum-queries
Youtube tutorial:
https://www.youtube.com/watch?v=ZBHKZF5w4YU

Idea:
O(n) time to build/update the tree, O(n) space to store the tree, O(lgn) time to do the query.
1. Segment tree helps with range query in an array, ie. range sum query, range minimum query.

2. The value of each node you occupy the tree based on the requirement of the problem you are trying to solve, for example, sum of child nodes if range sum, or minimum value of child nodes if range minimum, etc.

3. Split the array into half every time, the node each represents a range, leaf nodes represent a range of [i,i], where i is the index it's inside the original array. 
    For example, we have array of length 6, 
        root node -> [0,5]
        root.left -> [0,2]
        root.right -> [3,5] ... until leaf nodes

4. Overlaps
    a. Partial overlap -> looks deeper both left hand and right hand side, ie. node->[3,5], range:[2,4]
    b. Total overlap -> stop and return (node's range lies within query range, ie. node->[2,2], range:[2,4])
    c. No overlap -> stop and return

5. Number of nodes in the tree(denoted using an array), ie. i -> par (i-1)/2 -> left 2*i+1 -> right 2*i+2
    int len = originalArray.length;
    if len = 2^n, num of nodes = 2^n*2-1
    if len != 2^n, num of nodes = 2^n*2-1, where 2^n is smallest power of 2 that's larger than len
    (Averagely, it's O(4*n))
    for example, range maximum:
    len=4, {-1,0,3,6} -> {6,0,6,-1,0,3,6} 
    len=5, {-1,0,3,6,9} -> {9,3,9,0,3,6,9,-1,0,null,null,null,null,null,null}


/*Sample Code for range summation problem*/
// Build the tree from original array, lo and hi are original array indexs
void buildTree(vector<int>& arr, int lo, int hi, vector<int>& tree, int treeIndex) {
    if(lo==hi){ //lo==hi => leaf node
        tree[treeIndex] = arr[lo];
        return;
    }
    int mid = lo+(hi-lo)/2;
    buildTree(arr, treeIndex*2+1, lo, mid);
    buildTree(arr, treeIndex*2+2, mid+1, hi);

    tree[treeIndex] = merge(tree[treeIndex*2+1], tree[treeIndex*2+2]);
}

// Query for maximal value within range [i, j] of original array
int query(vector<int>& tree, int pos, int lo, int hi, int i, int j){
    if(lo> j || hi < i) return 0; // no overlap
    
    if(i<=lo && j>=hi) return tree[pos]; // total overlap
    
    if(i> mid) return query(tree, 2*pos+2, mid+1, hi, i, j);
    else if(j<= mid) return query(tree, 2*pos+1, lo, mid, i, j);

    int left = query(tree, 2*pos+1, lo, mid, i, mid);
    int right = query(tree, 2*pos+2, mid+1, hi, mid+1, j);

    return merge(left, right);
}














