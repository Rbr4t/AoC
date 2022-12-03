
with open('day1.txt', 'r') as f:
    lines = f.readlines()
    
    n = []
    p = []
    for x in lines:
        if x == "\n":
            n.append(sum(p))
            p = []
        else:
            p.append(int(x.strip()))


n.sort()
print(n[-1])

# total = max(n)
# n.remove(max(n))
# total += max(n)
# n.remove(max(n))
# total += max(n)

print(sum(n[-3:]))
