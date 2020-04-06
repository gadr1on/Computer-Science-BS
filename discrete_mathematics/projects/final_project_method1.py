#                      1     2     3     4     5     6     7     8     9
distances = { 1   : [  0  ,  31 ,  58 ,  27 ,  12 ,  10 ,  34 ,  64 ,  44 ],
              2   : [  31 ,  0  ,  83 ,  21 ,  36 ,  45 ,  35 ,  92 ,  102],
              3   : [  58 ,  83 ,  0  ,  66 ,  11 ,  78 ,  28 ,  39 ,  94 ],
              4   : [  27 ,  21 ,  66 ,  0  ,  88 ,  96 ,  67 ,  49 ,  50 ],
              5   : [  12 ,  36 ,  11 ,  88 ,  0  ,  43 ,  12 ,  33 ,  70 ],
              6   : [  10 ,  45 ,  78 ,  96 ,  43 ,  0  ,  23 ,  46 ,  83 ],
              7   : [  34 ,  35 ,  28 ,  67 ,  12 ,  23 ,  0  ,  30 ,  79 ],
              8   : [  64 ,  92 ,  39 ,  49 ,  33 ,  46 ,  30 ,  0  ,  92 ],
              9   : [  44 ,  102,  94 ,  50 ,  70 ,  83 ,  79 ,  92 ,  0  ]
            }


graph = { 1 : [6, 5, 4, 2],
          2 : [1, 5, 4],
          3 : [5],
          4 : [1, 2],
          5 : [1, 2, 3],
          6 : [1],
          7 : [6, 4],
          8 : [7],
          9 : [3, 8]
        }

th = 38
nodes = [1, 2, 3, 4, 5, 6, 7, 8, 9]
P = []

def cercano_and_distance(C):
    dist = {}
    for node in nodes:
        num = []
        for i in C:
            num.append(distances[node][i-1])
        dist[node] = sum(num)/len(num)
    if len(dist)==0:
        return [0, 0]
    clossest = min([dist[i] for i in dist])
    clossest_v = [i for i in dist if dist[i]==clossest][0]
    if clossest <= th:
        return [1, clossest_v]
    return [0, clossest_v]

def del_repetition(l):
    result = []
    el = []
    for i in l:
        x = []
        for j in i:
            if j not in el:
                el.append(j)
                x.append(j)
        result.append(x)
    return result



while len(nodes)!=0:
    C = [] # Start a new cluster
    V = [i for i in graph if len(graph[i])==max([len(graph[i]) for i in graph])][0] # Vertes of maximal degree in G
    del graph[V]
    C.append(V) # Add vertex V to C
    if V in nodes:
        nodes.remove(V) # Remove V from S
    b, x = cercano_and_distance(C) # Function that find close vertex 'x'
                     # b=1 (if x<= threshold) b=0 otherwise
    while b==1:
        # if x not in nodes:
        C.append(x)
        nodes.remove(x)
        b, x = cercano_and_distance(C)
    P.append(C)

print([i for i in del_repetition(P) if i!=[]])
