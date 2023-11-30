
class Stack:
    """
    Example data

    [D]        
    [N] [C]    
    [Z] [M] [P]

    [[['Z'], ['N'], ['D']], [['M'], ['C']], [['P']]]
    """
    columns = 3
    contents = [[['D'], ['M'], ['S'], ['Z'], ['R'], ['F'], ['W'], ['N']], [['W'], ['P'], ['Q'], ['G'], ['S']], 
    [['W'], ['R'], ['V'], ['Q'], ['F'], ['N'], ['J'], ['C']], [['F'], ['Z'], ['P'], ['C'], ['G'], ['D'], ['L']],
    [['T'], ['P'], ['S']], [['H'], ['D'], ['F'], ['W'], ['R'], ['L']], [['Z'], ['N'], ['D'], ['C']],
    [['W'], ['N'], ['R'], ['F'], ['V'], ['S'], ['J'], ['Q']], [['R'], ['M'], ['S'], ['G'], ['Z'], ['W'], ['V']]
    ]
    
    def __init__(self):
        pass

    def move_container(self, column_to, column_from):
        self.contents[column_to] += list(self.contents[column_from][-1])
        self.contents[column_from].pop(-1)
        
    def move_containers(self, column_to, column_from, amount):
        added = list(self.contents[column_from])
        self.contents[column_to] += added[len(added) - amount:]

        for value in range(amount):
            self.contents[column_from].pop(-1)
       

    def __str__(self):
        return str(self.contents)
    

def solution1():
    with open('/home/rbr4t/AoC/2022/day5.txt', 'r') as file:
        file = file.readlines()
        
        stack = Stack()

        instructions = []
        
        # get the instructions

        for ins in file[10:]:
            instructions.append(ins.strip())
        
        # parse instructions and do what they say
        for ins in instructions:
            move = int(ins.split(' ')[1])
            froM = int(ins.split(' ')[3]) -1
            to = int(ins.split(' ')[-1]) -1
           
            for x in range(move):
                stack.move_container(to, froM)
            
        
    ans = ''
    for x in stack.contents:
        ans += x[-1]
    print(ans)

def solution2():
    with open('/home/rbr4t/AoC/2022/day5.txt', 'r') as file:
        file = file.readlines()
        
        stack = Stack()

        instructions = []
        
        for ins in file[10:]:
            instructions.append(ins.strip())       

        for ins in instructions:
            amount = int(ins.split(' ')[1])
            froM = int(ins.split(' ')[3]) -1
            to = int(ins.split(' ')[-1]) -1
                    
            stack.move_containers(to, froM, amount) 
        
    ans = ''
    for x in stack.contents:
        ans += str(x[-1][0])
    print(ans)
        
solution1()
