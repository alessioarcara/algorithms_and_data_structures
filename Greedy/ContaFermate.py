from typing import List


def contaFermate(d: List[float], K: float, C: float):
    fuel = C
    f = 0
    for i in range(0, len(d) - 1):
        fuel -= d[i] / K
        if fuel < 0:
            return -1
        if fuel < d[i+1] / K:
            fuel = C
            f += 1
    return f


soste = [150, 700, 400, 300, 600, 300]
autonomia = 25
carburante = 40

print(contaFermate(soste, autonomia, carburante))
