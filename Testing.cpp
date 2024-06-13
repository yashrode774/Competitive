#include <iostream>
#include <vector>
#include <string>

using namespace std;

bool canPut(vector<int> arr, int mid, int k) {
  int i = 1;
  int count = 1;
  int last = arr[0]; //4
  while (i < arr.size()) {
      if (arr[i] - last >= mid) {
        last = arr[i];
        count++;

      }
      i++;
  }

  return count >= k;
}

// 2 2 3  1


int getHigh(vector<int> arr, int k) {
  int n = arr.size();
  int low = 0;
  int high = arr[n-1] -  arr[0];
  int ans = 0;
  while (high>=low) {
    int mid = low + (high-low) / 2;
      if (canPut(arr, mid, k)) {
        ans = max(ans, mid);
        low = mid + 1;
      }
      else {
        high = mid - 1;
      }
  }
  return ans;
}

int main() {
  int t;
  cin>>t;
  while (t--) {
    int k;
    cin>>k;
    int n;
    cin>>n;
    vector<int> arr;
    int x;
    while (n--) {
      cin>>x;
      arr.push_back(x);
    }
    sort(arr.begin(), arr.end());
    cout<<getHigh(arr, k)<<endl;
  }

  return 0;
}


