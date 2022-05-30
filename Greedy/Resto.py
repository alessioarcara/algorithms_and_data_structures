from typing import List


def restoGreedy(R: int, T: List[int]):
    T.sort(reverse=True)
    m = 0
    i = 0
    while(R > 0 and i <= len(T)):
        if R >= T[i]:
            R -= T[i]
            m += 1
        else:
            i += 1
    if R > 0:
        print("errore: resto non erogabile")
    else:
        return m


print(restoGreedy(113, [5, 2, 1]))
