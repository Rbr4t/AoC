
def soultion1():
    with open('2022/day3.txt', 'r') as file:
        file = file.readlines()
        result = 0
        for sack in file:
            mid = len(sack)//2
            first_section = sack[0:mid]
            second_section = sack[mid:]

            
            
            diff = list(set(first_section).intersection(second_section))[0]

            #print(result)
            if diff.isupper():
                result += ord(diff)- 38
            else:
                result += ord(diff)- 96
        print(result)

def solution2():
    with open('2022/day3.txt', 'r') as file:
        file = file.readlines()

        length_of_data = len(file)
        result = 0
        for i in range(100):
            print(file[i])
            diff = list(set(file[i*3].strip()).intersection(file[i*3+1].strip(), file[i*3+2].strip()))[0]
            print(diff)
            if diff.isupper():
                result += ord(diff)- 38
            else:
                result += ord(diff)- 96
        print(result)
solution2()
            