A={1:0, 2:0, 3:0}
B={1:0, 2:0, 3:0, 4:0, 5:0}

f = [[1,1],[2,3],[3,4]]
g = [[1,1],[2,3],[3,3]]

def reset():
    for i in A: A[i]=0
    for i in B: B[i]=0

def one2one(connections):
    reset()
    for c in connections:
        A[c[0]]+=1
        B[c[1]]+=1
    Acheck = [i for i in A if A[i]>1 or A[i]==0]
    Bcheck = [i for i in B if B[i]>1]
    if len(Acheck)>0 or len(Bcheck)>0:
        return False
    return True

def onto(connections):
    reset()
    for c in connections:
        A[c[0]]+=1
        B[c[1]]+=1
    Acheck = [i for i in A if A[i]>1 or A[i]==0]
    Bcheck = [i for i in B if B[i]==0]
    if len(Acheck)>0 or len(Bcheck)>0:
        return False
    return True

print("f one2one? %s" % one2one(f))
print("g one2one? %s" % one2one(g))
print("f onto? %s" % onto(f))
print("g onto? %s" % onto(g))
