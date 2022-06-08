from functools import wraps


def memo(func):
    cache = {}

    @wraps(func)
    def wrap(*args):
        if args not in cache:
            cache[args] = func(*args)
        return cache[args]
    return wrap


@memo
def fib(n):
    if n <= 2:
        return 1
    else:
        return fib(n-1) + fib(n-2)


def fib_mem(n, f):
    if n not in f:
        f[n] = fib_mem(n-1, f) + fib_mem(n-2, f)
    return f[n]


def fib_mem_driver(n):
    f = {}

    f[1] = 1
    f[2] = 1
    return fib_mem(n, f)


def fib_iter(n):
    if n <= 2:
        return 1
    else:
        f = []
        f.append(1)
        f.append(1)
    for i in range(2, n):
        f.append(f[i-1] + f[i-2])
    return f[n-1]


def fib_iter2(n):
    if n <= 2:
        return 1
    else:
        fn1 = fn2 = fn = 1
        for i in range(2, n):
            fn1 = fn2
            fn2 = fn
            fn = fn1 + fn2
    return fn


print(fib_mem_driver(500))
print(fib_iter(1000))
print(fib_iter2(1000))
