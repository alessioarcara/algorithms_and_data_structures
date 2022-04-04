# Lista bidirezionale circolare con sentinella
class LinkedList:
    class Node:
        def __init__(self, value):
            self.value = value
            self.pred = self
            self.succ = self

    def __init__(self):
        self.sentinel = self.Node(None)

    def head(self):
        return self.sentinel.succ

    def tail(self):
        return self.sentinel.pred

    def next(self, p):
        return p.succ

    def prev(self, p):
        return p.pred

    def isEmpty(self):
        return self.sentinel.succ == self.sentinel.pred == self.sentinel

    def finished(self, p):
        return self.sentinel == p

    def append(self, value):
        t = self.Node(value)
        t.pred = self.tail()
        t.succ = self.sentinel
        self.tail().succ = t
        self.sentinel.pred = t
        return t

    def insert(self, p, value):
        t = self.Node(value)
        t.pred = p.pred
        p.pred.succ = t
        t.succ = p
        p.pred = t
        return t

    def remove(self, p):
        p.pred.succ = p.succ
        p.succ.pred = p.pred
        t = p.succ
        del p
        return t

    def read(self, p):
        return p.value

    def write(self, p, value):
        p.value = value

    def printAll(self, p):
        if p.value == None:
            return
        else:
            print(p.value)
            self.printAll(p.succ)


list = LinkedList()
# x1 = list.insert(list.head(), 1)
# x2 = list.insert(x1, 2)
# x3 = list.insert(x2, 3)
list.append(1)
list.append(2)
list.append(3)
print(f"TESTA: {list.head().value}")
print(f"CODA: {list.tail().value}")
list.printAll(list.head())
