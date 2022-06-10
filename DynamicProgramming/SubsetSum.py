import time
import random


def calcTime(callback):
    start_time = time.time()
    callback()
    end_time = time.time()
    print(end_time - start_time)


def vecSum1(V):
    smax = 0
    for i in range(len(V)):
        for j in range(i, len(V)):
            smax = max(smax, sum(V[i:j+1]))
    return smax


def vecSum2(V):
    smax = 0
    for i in range(len(V)):
        sum = 0
        for j in range(i, len(V)):
            sum = sum + V[j]
            smax = max(smax, sum)
    return smax


def vecSumDI(V, i, j):
    if i == j:
        return V[i]
    else:
        m = (i+j)//2
        sleft = vecSumDI(V, i, m)
        sright = vecSumDI(V, m+1, j)
        sa = sb = s = 0
        for i in range(m - 1, i-1, -1):
            s = s + V[i]
            if (s > sa):
                sa = s
        s = 0
        for i in range(m + 1, j + 1):
            s = s + V[i]
            if (s > sb):
                sb = s
        return max(sleft, sright, V[m] + sa + sb)


def vecSumDP(V):
    S = [V[0]]
    idx_max = 0
    for i in range(1, len(V)):
        S.append(max(S[i-1] + V[i], V[i]))
        if S[i] > S[idx_max]:
            idx_max = i
    return S[idx_max]


A = [random.randint(-100, 100) for i in range(1000)]
calcTime(lambda: print(vecSum1(A)))
calcTime(lambda: print(vecSum2(A)))
calcTime(lambda: print(vecSumDI(A, 0, len(A)-1)))
calcTime(lambda: print(vecSumDP(A)))
