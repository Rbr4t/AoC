
with open('2022/day6.txt') as f:
    f = f.readline()

    signal = list(f)
    r = len(list(f))-14 # -3 on first task

    for x in range(r):
        # change x+14 to x + 4 when on first task
        if sorted(list(set(signal[x: x+14]))) == sorted(signal[x: x+14]):
            print(x+14)
            break
