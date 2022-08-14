def Hanoi(n, src, middle, dest):
    if n == 1:
        print(f"{src} -> {dest}")
    else:
        Hanoi(n-1, src, dest, middle)
        print(f"{src} -> {dest}")
        Hanoi(n-1, middle, src, dest)


Hanoi(3, 1, 2, 3)
