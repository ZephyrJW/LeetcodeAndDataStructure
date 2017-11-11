/*
 Given a complete binary tree, count the number of nodes.
 */
public int countNodes(TreeNode root) {
	/*Common scenario
	 if(root==null) return 0;
	 return 1+countNodes(root.left)+countNodes(root.right);
	 */	
	if(root==null) return 0;

	int lh=0, rh=0;
	TreeNode left=root, right=root;
	while(left!=null){
		left=left.left;
		lh++;
	}
	while(right!=null){
		right = right.right;
		rh++;
	}
	if(lh==rh) return (1<<lh)-1; // << operator has low priority
	return 1+countNodes(root.left)+countNodes(root.right);
}
