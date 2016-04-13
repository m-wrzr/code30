# https://code.google.com/codejam/contest/635101/dashboard#s=p0

# input() reads a string with a line of input, stripping the '\n' (newline) at the end.
# This is all you need for most Google Code Jam problems.

nCase = int(input())  # read a line with a single integer
fileres = ""

# adjust name depending on input
filename = "output_small.txt"

for i in range(1, nCase + 1):

    n, m = [int(s) for s in input().split(" ")]  # read a list of integers, 2 in this case

    exists = set()
    make = set()

    # loops add paths and subpaths to their relative sets

    for ignore in range(0, n):
        path = input()
        exists.add(path)

        paths = path.split('/')
        for k in range(0, len(paths)):
            exists.add('/'.join(paths[0:k]))

    for ignore in range(0, m):
        path = input()
        make.add(path)

        paths = path.split('/')
        for k in range(0, len(paths)):
            make.add('/'.join(paths[0:k]))

    # filter empty strings
    exists = set(filter(None, exists))
    make = set(filter(None, make))

    # add set difference as result
    fileres += "Case #{}: {}\n".format(i, len(make - exists))

# print to sout
print(fileres)

# write to txt
with open(filename, "w") as text_file:
    text_file.write(fileres)
