input = [1,2,3,4,2,5,6,2,7,8,2,4,9,2,12,13,5,2,8,15]
set1 = [0,0]
set2 = [0,0]
set1pos = 0
set2pos = 0
flag = 1
hits = 0
posi = 0


for i in input:
    hit = True if i in set1+set2 else False
    if hit:
        hits+=1
        print(i,set1,set2,"HIT!!")
    else:
        if flag == 1:
            set1[1]=set1[0]
            set1[0]=i
            if set1pos == 0:
                set1pos+=1
            else:
                set1pos-=1
            flag+=1
        else:
            set2[1]=set2[0]
            set2[0]=i
            if set2pos == 0:
                set2pos+=1
            else:
                set2pos-=1
            flag-=1
        print(i,set1,set2)
    posi+=1

print('HITS: %d' % hits)
