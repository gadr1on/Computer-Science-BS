input = [1,2,3,4,2,5,6,2,7,8,2,4,9,2,12,13,5,2,8,15]
set1 = [0,0]
set2 = [0,0]
set1pos = 0
set2pos = 0
flag = 1
hits = 0
posi = 0


def readFuture(set, setpos):
    rep1, rep2 = [], []
    for j in range(posi, len(input)):
        if set[0]==input[j]:
            rep1.append(input[j])
        if set[1]==input[j]:
            rep2.append(input[j])
    if len(rep1)==len(rep2):
        set[setpos]=i
    else:
        if len(rep1)<len(rep2):
            set[0]=i
        else:
            set[1]=i


for i in input:
    hit = True if i in set1+set2 else False
    if hit:
        hits+=1
        print(i,set1,set2,"HIT!!")
    else:
        if flag == 1:
            readFuture(set1, set1pos)
            if set1pos == 0:
                set1pos+=1
            else:
                set1pos-=1
            flag-=1
        else:
            readFuture(set2, set2pos)
            if set2pos == 0:
                set2pos+=1
            else:
                set2pos-=1
            flag+=1
        print(i,set1,set2)
    posi+=1

print('HITS: %d' % hits)
