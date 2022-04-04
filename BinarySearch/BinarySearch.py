def binarySearch(A, v, low, high):
    """
    A: sorted array
    v: element to search in array
    low, high: start and end positions of array
    """
    if low > high:
        return (-1)
    else:
        m = (low + high) // 2
        if (A[m] == v):
            return m
        elif A[m] < v:
            return binarySearch(A, v, m+1, high)
        else:
            return binarySearch(A, v, low, m - 1)


A = [10, 24, 32, 50]

print(
    f"l'elemento si trova nella posizione: {binarySearch(A, 50, 0, len(A) - 1)}")
