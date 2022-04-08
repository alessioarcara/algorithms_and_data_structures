confronti = 0


def selectionSort(A: list):
    for i in range(len(A)):
        j = min(A, i)
        swap = A[i]
        A[i] = A[j]
        A[j] = swap


def min(A, k):
    global confronti
    min = k
    for h in range(k+1, len(A)):
        confronti += 1
        if A[h] < A[min]:
            min = h
    return min


A = [4, 100, 32, 11, 88, 50, 32, 1]
selectionSort(A)
print(f"{confronti / (len(A))} confronti")
