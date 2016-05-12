from decimal import *

getcontext().prec = 10

nCase = int(input())  # read a line with a single integer
fileres = ""

# adjust name depending on input
filename = "output_large.txt"

for i in range(1, nCase + 1):

    # N is the number of dimensions and M the number of queries
    n, m = [int(s) for s in input().split(" ")]

    # length of dimensions
    dims = [int(s) for s in input().split(" ")]

    fileres += "Case #{}:\n".format(i)

    # M lines follow. In the ith line, there are two integers Li and Ri,
    # which give the range of dimensions to use for the ith subproblem
    for j in range(0, m):

        l, r = [int(s) for s in input().split(" ")]

        # dims cuboid/cube
        d = r - l + 1

        volume = Decimal(1)
        for k in range(l, r + 1):
            volume *= dims[k]

        res = volume ** (Decimal(1) / Decimal(d))
        fileres += "{}\n".format(res)

# print to sout
print(fileres)

# write to txt
with open(filename, "w") as text_file:
    text_file.write(fileres)
