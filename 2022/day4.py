


with open('2022/day4.txt', 'r') as f:
    f = f.readlines()
    result = 0

    # part 1 - 24ms
    for el in f:
        elf1 = el.split(',')[0]
        elf2 = el.split(',')[1].strip()
        A = int(elf1.split('-')[0])
        B = int(elf1.split('-')[-1])
        C = int(elf2.split('-')[0])
        D = int(elf2.split('-')[-1])
        # 55-99,54-56
        #print(elf1)
        if (A <= C and B >= D) or (C <= A and D >=B):
            result += 1
            #print(el)
        
    print(result)
    result = 0
    # part2 - 33ms
    for el in f:
        elf1 = el.split(',')[0]
        elf2 = el.split(',')[1].strip()
        A = int(elf1.split('-')[0])
        B = int(elf1.split('-')[-1])
        C = int(elf2.split('-')[0])
        D = int(elf2.split('-')[-1])
        #print(el.strip())
        if len(range(A, B+1)) > len(range(C, D+1)):
            if set(range(A, B+1)).intersection(set(range(C, D+1))) != set():
                result += 1
                #print(set(range(A, B+1)).intersection(set(range(C, D+1))))
        else:
            if set(range(C, D+1)).intersection(set(range(A, B+1))) != set():
                #print(set(range(C, D+1)).intersection(set(range(A, B+1))))
                result += 1
    print(result)