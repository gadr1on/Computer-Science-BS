# Ceating nodes
dots = {1:0,2:0,3:0,4:0,5:0,6:0,7:0,8:0}

# Making connections
connect = [[7,1],[7,2],[2,1],[2,4],[1,6],[4,6],[4,3],[1,3],[5,4],[3,5],[5,8],[3,8],[6,8]]

# For loop that counts the number of
for c in connect:
    for n in c:
        dots[n]+=1

# Finding the max degree
max_degree = max([dots[i] for i in range(1,9)])

# Printing all the nodes with max degree
for dot in dots:
    if max_degree==dots[dot]:
        print(dot)
