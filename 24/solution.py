inputSize = "large"

with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())
    d = {'O': 0, 'B': 1}

    for case in range(0, nCase):
        # options press, move, wait
        # buttons 1 to 100
        # example 4 - O 2 B 1 B 2 O 4

        inputs = file.readline().split(" ")[1:]
        seq = [(d[inputs[i]], int(inputs[i + 1])) for i in range(0, len(inputs), 2)]

        res = 0

        pos = [1, 1]
        time = [0, 0]

        for i, x in seq:
            diff = abs(x - pos[i])
            time[i] += diff

            pos[i] = x
            time[i] = max(time[i], res)

            time[i] += 1
            res = time[i]

        output += "Case #{}: {}\n".format(case + 1, res)

print(output)

# write to txt
with open("A-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)
