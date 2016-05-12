inputSize = "large"

with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(0, nCase):
        n, k = [int(i) for i in file.readline().split(" ")]

        # analysis for large dataset, my naive approach was too slow
        # on if rightmost n bits are 1
        output += "Case #{}: {}\n".format(case + 1, "ON" if ((k % (1 << n)) + 1) == (1 << n) else "OFF")

print(output)

# write to txt
with open("A-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)
