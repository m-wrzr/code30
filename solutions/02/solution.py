# base 2 till 10 possible as each symbol is a digit

nCase = int(input())  # read a line with a single integer
fileres = ""

# adjust name depending on input
filename = "output_small.txt"

for i in range(1, nCase + 1):

    num = input()
    base = set()

    for c in list(num):
        base.add(c)

    baseval = len(base)
    if baseval == 1:
        baseval = 2

    vals = {num[0]: 1}
    base.remove(num[0])

    # add zero as next value
    for j in range(1, len(num)):
        if num[j] in base:
            base.remove(num[j])
            vals[num[j]] = 0
            break

    # add other values
    for j in range(1, len(num)):
        if num[j] in base:
            vals[num[j]] = baseval - len(base)
            base.remove(num[j])

    # add up string
    res = 0
    for j, c in enumerate(num):
        res += vals[c] * (baseval ** (len(num) - 1 - j))

    fileres += "Case #{}: {}\n".format(i, res)

# print to sout
print(fileres)

# write to txt
with open(filename, "w") as text_file:
    text_file.write(fileres)
