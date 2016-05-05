inputSize = "large"

with open("B-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(0, nCase):

        # n s p
        inputs = [int(i) for i in file.readline().split()]
        n = inputs[0]
        s = inputs[1]
        p = inputs[2]
        scores = inputs[3:(n + 3)]

        surp = p
        good = p

        # define reachable surprising/good results
        if p > 1:
            surp = (3 * p) - 4
            good = (3 * p) - 2

        result = 0

        # for each score, if achievable is result, or if achievable and surprise left increment
        for score in scores:
            if score >= good:
                result += 1
            elif score >= surp and s > 0:
                result += 1
                s -= 1

        output += "Case #{}: {}\n".format(case + 1, result)

print(output)

# write to txt
with open("B-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)
