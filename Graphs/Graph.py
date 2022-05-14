class Graph:
    def __init__(self) -> None:
        self.nodes = {}

    def V(self):
        return self.nodes.keys()

    def size(self):
        return len(self.nodes)

    def adj(self, u):
        if u in self.nodes:
            return self.nodes[u]

    def insertNode(self, u):
        if u not in self.nodes:
            self.nodes[u] = {}

    def insertEdge(self, u, v, w=0):
        self.insertNode(u)
        self.insertNode(v)
        self.nodes[u][v] = w


graph = Graph()
for u, v in [('a', 'b'), ('a', 'd'), ('b', 'c'), ('d', 'a'), ('d', 'c'), ('d', 'e'), ('e', 'c')]:
    graph.insertEdge(u, v)

for u in graph.V():
    print(u, '->', graph.adj)
