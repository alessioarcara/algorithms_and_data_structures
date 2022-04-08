def insertionSort(arr):
    for i in range(1, len(arr)):
        temp = arr[i]
        j = i
        while j >= 1 and arr[j-1] > temp:
            arr[j] = arr[j-1]
            j = j-1
        arr[j] = temp


A = [4, 100, 32, 11, 88, 50, 32, 1]
insertionSort(A)
print(A)
