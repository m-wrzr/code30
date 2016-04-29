inputSize = "large"


def checkHorizontal(player, g):
    for j in range(4):
        if all((e == player or e == "T") for e in g[j]):
            return True
    return False


def checkVertical(player, g):
    for j in range(4):
        # not that nice, but limit is always 4, so should be fine
        if all((e == player or e == "T") for e in (g[0][j] + g[1][j] + g[2][j] + g[3][j])):
            return True
    return False


def checkDiagonal(player, g):
    if all((e == player or e == "T") for e in (g[0][0] + g[1][1] + g[2][2] + g[3][3])):
        return True
    if all((e == player or e == "T") for e in (g[3][0] + g[2][1] + g[1][2] + g[0][3])):
        return True
    return False


def gameFull(g):
    for j in range(4):
        if any(e == "." for e in (g[j])):
            return False
    return True


with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(1, nCase + 1):

        grid = [None] * 4

        for i in range(4):
            grid[i] = list(file.readline().replace("\n", ""))

        xWon = checkDiagonal("X", grid) or checkHorizontal("X", grid) or checkVertical("X", grid)
        oWon = checkDiagonal("O", grid) or checkHorizontal("O", grid) or checkVertical("O", grid)

        if (xWon and oWon) or (not (xWon or oWon) and gameFull(grid)):
            output += "Case #{}: Draw\n".format(case)
        elif not (xWon or oWon):
            output += "Case #{}: Game has not completed\n".format(case)
        elif xWon:
            output += "Case #{}: X won\n".format(case)
        else:
            output += "Case #{}: O won\n".format(case)

        # disregard empty
        file.readline()

    print(output)

    # write to txt
    with open("A-" + inputSize + "-output.txt", "w") as text_file:
        text_file.write(output)
