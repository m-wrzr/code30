inputSize = "large"

with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(0, nCase):

        inputs = file.readline().split()

        standing = 0
        friendsNeeded = 0

        for i, amount in enumerate([int(s) for s in list(inputs[1])]):
            friendsNeeded = max(friendsNeeded, i - standing)
            standing += amount

        output += "Case #{}: {}\n".format(case + 1, friendsNeeded)

print(output)

# write to txt
with open("A-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)



