UNIVERSAL = ["Ponce","Bayamon","Carolina","San Juan","Fajardo","Vieques","Culebra",
             "Canovanas","Las Piedras","Mona"]
A = ["Bayamon","Ponce","Mona","Canovanas"]
B = ["Vieques","Bayamon","Carolina","San Juan"]
_A = [x for x in UNIVERSAL if (x not in A)]
_B = [x for x in UNIVERSAL if (x not in B)]

UNION=set([x for x in A+B if (x in A) or (x in B)])
COMPLEMENT_UNION=set([x for x in UNIVERSAL if (x not in UNION)])
COMPLEMENT_INTERSECTION=set([x for x in _A+_B if (x in _A) and (x in _B)])

print("UNIVERSAL: %s" % UNIVERSAL)
print("UNION: %s" % UNION)
print("COMPLEMENT_UNION: %s" % COMPLEMENT_UNION)
print("COMPLEMENT_INTER: %s" % COMPLEMENT_INTERSECTION)
print("COMPLEMENT_INTER = COMPLEMENT_UNION")
