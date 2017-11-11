/*Operations:
 1) getMin(): It returns the root element of Min Heap. Time Complexity of this operation is O(1).

 2) extractMin(): Removes the minimum element from Min Heap. Time Complexity of this Operation is O(Logn) as this operation needs to maintain the heap property (by calling heapify()) after removing root.

 3) decreaseKey(): Decreases value of key. Time complexity of this operation is O(Logn). If the decreases key value of a node is greater than parent of the node, then we don’t need to do anything. Otherwise, we need to traverse up to fix the violated heap property.

 4) insert(): Inserting a new key takes O(Logn) time. We add a new key at the end of the tree. IF new key is greater than its parent, then we don’t need to do anything. Otherwise, we need to traverse up to fix the violated heap property.

 5) delete(): Deleting a key also takes O(Logn) time. We replace the key to be deleted with minum infinite by calling decreaseKey(). After decreaseKey(), the minus infinite value must reach root, so we call extractMin() to remove key.
 */

using namespace std;

void swap(int* x, int* y);

class MinHeap{
	int* harr;
	int capacity;
	int heap_size;
public:
	MinHeap(int capacity);

	// to heapify the subtree rooted at i
	void heapify(int i);

	int parent(int i){return (i-1)/2;}
	int left(int i){return 2*i+1;}
	int right(int i){return 2*2+2;}

	int extractMin();

	void decreaseKey(int i, int new_val);

	int getMin() {return harr[0];}

	void deleteKey(int i);

	void insertKey(int k);
};

MinHeap::MinHeap(int cap){
	heap_size = 0;
	capacity = cap;
	harr = new int[cap];
}

void MinHeap::insertKey(int k){
	if(heap_size==capacity){
		cout << "\nOverflow: Exceed Capacity\n" ;
		return;
	}
	
	heap_size++;
	int i=heap_size-1;
	harr[i] = k;

	//Maintain MinHeap property
	while(i!=0 && harr[i]< harr[parent(i)]){
		swap(&harr[i], &harr[parent(i)]);
		i = parent(i);
	}
}

void MinHeap::decreaseKey(int i, int new_val){
	harr[i] = new_val;
	while(i!=0 && harr[i]< harr[parent(i)]){
		swap(&harr[i], &harr[parent(i)]);
		i=parent(i);
	}
}

int MinHeap::extractMin() {
	if(heap_size<=0) return INT_MAX;
	if(heap_size==1){
		heap_size--;
		return harr[0];
	}

	int res = harr[0];
	harr[0] = harr[heap_size-1];
	heap_size--;
	MinHeapify(0);

	return res;
}

void MinHeap::deleteKey(int i){
	decreaseKey(i, INT_MIN);
	extractMin();
}

void MinHeap::MinHeapify(int i){
	int l = left(i);
	int r = right(i);
	int smallest = i;
	if(l< heap_size && harr[l]< harr[smallest]){
		smallest = l;
	}
	if(r< heap_size && harr[r]< harr[smallest]){
		smallest = r;
	}

	if(smallest != i){
		swap(&harr[smallest], &harr[i]);
		MinHeapify(smallest);
	}
}

void swap(int *x, int *y){
	int tmp = *x;
	*x = *y;
	*y = tmp;
}

