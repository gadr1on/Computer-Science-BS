input = [1,2,3,4,2,5,6,2,7,8,2,4,9,2]
set1 = [0,0]
set2 = [0,0]
set3 = [0,0]
set4 = [0,0]
flag = 1
hits = 0
posi = 0


for i in input:
    hit = True if i in set1+set2+set3+set4 else False
    if hit:
        hits+=1
        print(i,set1,set2,set3,set4,"HIT!!")
    else:
        if flag == 1:
            set1[1]=set1[0]
            set1[0]=i
            flag+=1
        elif flag == 2:
            set2[1]=set2[0]
            set2[0]=i
            flag+=1
        elif flag == 3:
            set3[1]=set3[0]
            set3[0]=i
            flag+=1
        else:
            set4[1]=set4[0]
            set4[0]=i
            flag=1
        print(i,set1,set2,set3,set4,)
    posi+=1

print('HITS: %d' % hits)
