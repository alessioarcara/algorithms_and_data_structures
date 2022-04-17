import time


def calcTime(callback):
    start_time = time.time()
    callback()
    end_time = time.time()
    print(end_time - start_time)


def bubbleSort(arr):
    swapped = False
    for i in range(len(arr)):
        for j in range(1, len(arr) - i):
            if arr[j-1] > arr[j]:
                temp = arr[j]
                arr[j] = arr[j-1]
                arr[j-1] = temp
                swapped = True
        if not swapped:
            break


start = 1
end = 10000
A = list(range(start, end))
B = list(range(end, start, -1))

calcTime(lambda: bubbleSort(A))
calcTime(lambda: bubbleSort(B))
