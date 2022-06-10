def knapsack_rec(w, v, i, j):
    if j == 0:
        return 0.0
    elif i == 0:
        return v[i] if j >= w[i] else 0
    else:
        if (j >= w[i]):
            return max(knapsack_rec(w, v, i-1, j), knapsack_rec(w, v, i-1, j-w[i]) + v[i])
        else:
            return knapsack_rec(w, v, i-1, j)


def knapsack_mem(w, v, n, C):
    DP = [[-1]*(C+1) for i in range(n+1)]
    return knapsack_mem_rec(w, v, n, C, DP)


def knapsack_mem_rec(w, v, i, j, DP):
    if j == 0:
        return 0.0
    elif i == 0:
        return v[i] if j >= w[i] else 0
    else:
        if DP[i][j] < 0:
            if j >= w[i]:
                DP[i][j] = max(knapsack_rec(w, v, i-1, j),
                               knapsack_rec(w, v, i-1, j-w[i]) + v[i])
                return DP[i][j]
            else:
                DP[i][j] = knapsack_rec(w, v, i-1, j)
                return DP[i][j]
        return DP[i][j]


def knapsack_dp(w, v, n, C):
    DP = [[0 for i in range(C + 1)] for j in range(n + 1)]

    for i in range(0, n + 1):
        for j in range(0, C + 1):
            if j == 0:
                DP[i][0] = 0
            elif j == 0:
                DP[0][j] = 0 if j < w[0] else v[0]
            else:
                if j >= w[i]:
                    DP[i][j] = max(DP[i-1][j-w[i]]+v[i], DP[i-1][j])
                else:
                    DP[i][j] = DP[i-1][j]

    return DP[n][C]


C = 10
n = 4
w = [2, 7, 6, 4]
v = [12.7, 6.4, 1.7, 0.3]
print(knapsack_rec(w, v, n-1, C))
print(knapsack_mem(w, v, n-1, C))
print(knapsack_dp(w, v, n-1, C))
