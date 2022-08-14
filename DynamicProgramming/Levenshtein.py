def dist(S: str, T: str,):
    n, m = len(S), len(T)
    L = [[0 for i in range(m + 1)] for j in range(n + 1)]

    # CASI BASE
    for i in range(n + 1):
        L[i][0] = i
    for j in range(1, m + 1):
        L[0][j] = j

    # CASI GENERALE
    for i in range(1, n + 1):
        for j in range(1, m+1):
            L[i][j] = min(L[i-1][j-1] + (0 if S[i-1] == T[j-1] else 1),
                          L[i-1][j] + 1,
                          L[i][j-1] + 1)
    return L[n][m]


print(dist("patto", "gatto"))
