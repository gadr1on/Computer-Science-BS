def interest(principal, percent, years):
    if years == 0:
        return principal
    else:
        return interest(principal * percent, percent, years-1)

print(interest(1000,1.06,1))
