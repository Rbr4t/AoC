
with open('test.txt', 'r') as f:
    lines = f.readlines()
    print(lines)
    n = []
    p = []
    for x in lines:
        if x == "\n":
            n.append(sum(p))
            p = []
        else:
            p.append(int(x.strip()))


print(max(n))

total = max(n)
n.remove(max(n))
total += max(n)
n.remove(max(n))
total += max(n)

print(total)
