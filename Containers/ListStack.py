import LinkedList


class ListStack:
    def __init__(self):
        self.linkedList = LinkedList.LinkedList()

    def isEmpty(self):
        return self.linkedList.isEmpty()

    def top(self):
        if not self.linkedList.isEmpty():
            return self.linkedList.head().value

    def pop(self):
        if not self.linkedList.isEmpty():
            t = self.linkedList.head()
            self.linkedList.remove(t)
            return t.value

    def push(self, v):
        self.linkedList.insert(self.linkedList.head(), v)


# listStack = ListStack()
# listStack.push(1)
# listStack.push(2)
# listStack.push(3)
# listStack.push(4)
# listStack.pop()
# listStack.pop()
# print(listStack.top())
