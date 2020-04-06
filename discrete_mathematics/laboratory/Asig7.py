def C(n,m):
    if m==0 or n==m:
        return 1
    else:
        return C(n-1,m-1)+C(n-1,m)

print(C(3,2))
