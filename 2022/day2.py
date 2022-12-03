
def solution1():
    with open('2022/day2.txt', 'r') as file:
        file = file.readlines()
        correspondence = {
            'X': 'A',
            'Y': 'B',
            'Z': 'C'
        }
        conditions_for_win = {
            'A': 'Y',
            'B': 'Z',
            'C': 'X',
            
        }

        scores_for_turns = {
            'X': 1,
            'Y': 2,
            'Z': 3
        }



        score = 0

        for el in file:
            
            el = el.split(' ')
            #print(el[0])
            #print(el[0] == el[1].strip())
            if el[1].strip() == conditions_for_win[el[0]]:
                score += 6
            
            elif el[0] == correspondence[el[1].strip()]:
                score += 3
            
            
            score += scores_for_turns[el[1].strip()]

        print(score)

solution1()

def solution2():
    with open('2022/day2.txt', 'r') as file:
        file = file.readlines()

        score = 0

        moves = {
            'A': 1,
            'B': 2,
            'C': 3
        }

        
        for el in file:
            el = el.split(' ')
            
            if el[1].strip("\n") == 'X':
                score += 0
                if el[0] == 'A':
                    score += 3
                elif el[0] == 'B':
                    score += 1
                else:
                    score += 2
            elif el[1].strip("\n") == 'Z':
                score += 6
                if el[0] == 'A':
                    score += 2
                elif el[0] == 'B':
                    score += 3
                else:
                    score += 1
            else:
                score += 3
                score += moves[el[0]]
            
    print(score)
solution2()