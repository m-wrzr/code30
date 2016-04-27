# solved only for small, even code jam formulas take super super long ...

inputSize = "large"
output = ""

fileInput = "B-" + inputSize + "-practice.in"
fileOutput = "B-" + inputSize + "-output.txt"

file = open(fileInput, "r")

nCase = int(file.readline())

for case in range(1, nCase + 1):
    d, k, n = [int(e) for e in file.readline().split(" ")]
    dancers = list(range(1, d + 1))

    # basic formula from analysis, it has to be differentiated between an even/odd k
    L = ((k - 2 + 2 * n) % d) + 1
    R = ((k + 2 * n) % d) + 1

    output += "Case #{}: {} {}\n".format(case, L, R)

    # NAIVE SOLUTION WORKED FOR SMALL INPUT
    #
    # for i in range(1, n + 1):
    #     if i % 2 == 0:
    #         dancers = counterclockvise(dancers, d)
    #     else:
    #         dancers = clockvise(dancers, d)
    #
    # output += "Case #{}: {} {}\n".format(case, dancers[(dancers.index(k) + 1) % d],
    #  dancers[(dancers.index(k) - 1) % d])

    '''
    def clockvise(list, len):
        for i in range(0, len, 2):
            tmp = list[i + 1]
            list[i + 1] = list[i]
            list[i] = tmp

        return list


    def counterclockvise(list, len):
        for i in range(1, len, 2):
            tmp = list[(i + 1) % len]
            list[(i + 1) % len] = list[i]
            list[i] = tmp

        return list
'''

print(output)

# write to txt
with open(fileOutput, "w") as text_file:
    text_file.write(output)
