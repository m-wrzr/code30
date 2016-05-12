# relatively easy, just set intersection for rows

inputSize = "small"
output = ""

fileInput = "A-" + inputSize + "-practice.in"
fileOutput = "A-" + inputSize + "-output.txt"

file = open(fileInput, "r")

nCase = int(file.readline())

for case in range(1, nCase + 1):

    first = int(file.readline())
    fst = [None] * 4
    for i in range(0, 4):
        fst[i] = [int(e) for e in file.readline().replace("\n", "").split(" ")]

    second = int(file.readline())
    snd = [None] * 4
    for i in range(0, 4):
        snd[i] = [int(e) for e in file.readline().replace("\n", "").split(" ")]

    res = set(fst[first - 1]).intersection(set(snd[second - 1]))

    if len(res) == 0:
        output += "Case #{}: Volunteer cheated!\n".format(case)
    elif len(res) == 1:
        output += "Case #{}: {}\n".format(case, next(iter(res)))
    else:
        output += "Case #{}: Bad magician!\n".format(case)

print(output)

# write to txt
with open(fileOutput, "w") as text_file:
    text_file.write(output)
