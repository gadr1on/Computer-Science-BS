UNIVERSAL = ["Ponce","Bayamon","Carolina","San Juan","Fajardo","Vieques","Culebra",
             "Canovanas","Las Piedras","Mona"]
A = ["Bayamon","Ponce","Mona","Canovanas"]
B = A[::-1]
C = ["Culebra","Canovanas","Las Piedras","Mona"]

A_INT_C=set([x for x in A+C if (x in A) and (x in C)])
B_INT_C=set([x for x in B+C if (x in B) and (x in C)])

print("UNIVERSAL: %s" % UNIVERSAL)
print("A: %s" % A)
print("B: %s" % B)
print("A_^_C: %s" % A_INT_C)
print("B_^_C: %s" % B_INT_C)
print("A_^_C=B_^_C")
