inputSize = "large"
output = ""

fileInput = "B-" + inputSize + "-practice.in"
fileOutput = "B-" + inputSize + "-output.txt"

file = open(fileInput, "r")

nCase = int(file.readline())


# we want happy side up all the time
# action flaps stack 1-i
# - - + - flip top 3
# -> - + + -

def reverse(mylist, bottom):
    mylist[0:bottom] = mylist[0:bottom][::-1]

    for j in range(0, bottom):

        if mylist[j] == "+":
            mylist[j] = "-"
        else:
            mylist[j] = "+"

    return mylist


for i in range(1, nCase + 1):

    stack = list(file.readline().replace("\n", ""))

    nAction = 0

    for neg in range(len(stack))[::-1]:

        # regular turnaround
        if stack[neg] == "-" and stack[0] != "+":

            stack = reverse(stack, neg + 1)
            nAction += 1

        # if swap would result in sad face turn top before
        elif stack[neg] == "-" and stack[0] == "+":

            # flip all on top and do loop again
            for k in range(0, len(stack)):
                if stack[k] == "-":
                    stack = reverse(stack, k)
                    nAction += 1
                    break

            stack = reverse(stack, neg + 1)
            nAction += 1

    print(stack)

    output += "Case #{}: {}\n".format(i, nAction)

print(output)

# write to txt
with open(fileOutput, "w") as text_file:
    text_file.write(output)
