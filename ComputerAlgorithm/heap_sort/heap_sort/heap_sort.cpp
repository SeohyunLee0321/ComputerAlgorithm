#include <iostream>
using namespace std;

const int SIZE = 15;
int L[SIZE] = { 10, 4, 7, 1, -2, 12, 28, 66, 9, 3, 5, 7, 6, 21, 11 };


void print_data()
{
	for (int i = 0; i < SIZE; i++)
		cout << " " << L[i] << " ";
	cout << endl;
}

void swap(int* x, int* y) {
	int temp;
	temp = *x;
	*x = *y;
	*y = temp;
}

void max_heap(int root, int n) {
	int root_data = L[root];
	int child = root * 2 + 1;
	while (child <= n - 1) {
		if (child < n - 1 && L[child] < L[child + 1])
			child += 1;
		if (root_data > L[child])
			break;
		else {
			L[(child - 1) / 2] = L[child];
			child = child * 2 + 1;
		}
	}
	L[(child - 1) / 2] = root_data;
}

void heap_sort() {
	for (int i = (SIZE - 2) / 2; i >= 0; i--)
		max_heap(i, SIZE);
	for (int i = SIZE - 1; i > 0; i--) {
		swap(L[0], L[i]);
		max_heap(0, i);
	}
}

void main()
{
	cout << "Input Data : ";
	print_data();

	heap_sort();

	cout << "\n\nSorted Data : ";
	print_data();

}