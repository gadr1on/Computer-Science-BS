def interest(principal, interest, years):
    for period in range(years):
        total = float(principal) * float((1+interest)**float(period+1))
    return  total

print(interest(1000, 0.06, 1))
