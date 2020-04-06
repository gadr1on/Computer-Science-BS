UNIVERSAL = ["Ponce","Bayamon","Carolina","San Juan","Fajardo","Vieques","Culebra",
             "Canovanas","Las Piedras","Mona"]
A = ["Bayamon","Ponce","Mona","Canovanas"]
B = ["Vieques","Bayamon","Carolina","San Juan"]

# AU(A^B) = A
INTERSC=[x for x in A+B if (x in A) and (x in B)]
UNION=set([x for x in A+INTERSC if (x in A) or (x in INTERSC)])

print("UNIVERSAL: %s" % UNIVERSAL)
print("A: %s" % A)
print("AU(A^B): %s" % UNION)
print("AU(A^B) = A")
