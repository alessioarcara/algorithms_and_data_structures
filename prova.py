def min(A):
    min = A[0]
    for i in range(1, len(A), 1):
        if A[i] < min:
            min = A[i]

    return min


A = [10, 4, 7, 3]

print(f'il minimo è: {min(A)}')
