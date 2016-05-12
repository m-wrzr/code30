inputSize = "large"

with open("A-" + inputSize + "-practice.in", "r") as file:
    output = ""
    nCase = int(file.readline())

    for case in range(0, nCase):
        switch = 0

        S = int(file.readline())
        engines = set()

        for ignore in range(S):
            engines.add(file.readline())

        Q = int(file.readline())
        processed = set()
        
        for i in range(Q):
            query = file.readline()

            # all other engine names already used - switch
            if query not in processed and len(processed) + 1 == len(engines):
                processed = set()
                switch += 1

            processed.add(query)

        output += "Case #{}: {}\n".format(case + 1, switch)

print(output)

# write to txt
with open("A-" + inputSize + "-output.txt", "w") as text_file:
    text_file.write(output)
