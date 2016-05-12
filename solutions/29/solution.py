inputSize = "large"

with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(0, nCase):
        r, c, w = [int(s) for s in file.readline().replace("\n", "").split(" ")]

        if w == 1:
            output += "Case #{}: {}\n".format(case + 1, r * c)

        elif c == w:
            output += "Case #{}: {}\n".format(case + 1, (r - 1) + w)

        else:
            # steps of w for each and for each ship part
            total = int(c / w) * r + w

            # if ship perfectly fits column, it has to be starting from last square
            if c % w == 0:
                total -= 1

            output += "Case #{}: {}\n".format(case + 1, total)

print(output)

# write to txt
with open("A-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)