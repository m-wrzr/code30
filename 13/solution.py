# solution is not really efficient, workaround with deadline for execution if a partial solution is not promising
# maybe revisit and fix, but the problem is not that interesting imo

from math import sqrt
from itertools import count, islice
import signal


# returns the value of a jamcoin in base x - output as base 10
def getvalueforbase(coin, base, n):
    res = 0
    for i, c in enumerate(coin):
        res += c * (base ** (n - 1 - i))
    return res


class TimedOutExc(Exception):
    pass


def deadline(timeout, *args):
    def decorate(f):
        def handler(signum, frame):
            raise TimedOutExc()

        def new_f(*args):
            signal.signal(signal.SIGALRM, handler)
            signal.alarm(timeout)
            return f(*args)
            signa.alarm(0)

        new_f.__name__ = f.__name__
        return new_f

    return decorate


# check if a number is prime
# efficient solution from
# http://stackoverflow.com/questions/4114167/checking-if-a-number-is-a-prime-number-in-python
@deadline(5)
def isprime(n):
    try:
        if n < 2:
            return 1
        for number in islice(count(2), int(sqrt(n) - 1)):
            if not n % number:
                return number
        return -1
    except TimedOutExc as e:
        return -1


# super weird coin increment - this code is stupid
def updatejamcoin(coin, n):
    return '1' + '{0:b}'.format(int("".join([str(e) for e in coin[1:(n - 1)]]), 2) + 1).zfill(n - 2) + '1'


inputSize = "large"
output = "Case #1:\n"

fileInput = "C-" + inputSize + "-practice.in"
fileOutput = "C-" + inputSize + "-output.txt"

file = open(fileInput, "r")
nCase = int(file.readline())

# n is length of jamcoin, j is amount distinct
n, j = [int(s) for s in file.readline().split(" ")]

# init jamcoin
jamcoin = [0] * n
jamcoin[0] = 1
jamcoin[n - 1] = 1

while j > 0:

    print(jamcoin)
    allbase = []

    # get according decimal values
    for i in range(2, 11):
        allbase.append(getvalueforbase(jamcoin, i, n))

    primes = [isprime(i) for i in allbase]

    if -1 not in primes:
        j -= 1

        output += "".join(str(s) for s in jamcoin)
        for res in primes:
            output += " " + str(res)
        output += "\n"
        print("found solution {} to go".format(j))

    # update jamcoin
    jamcoin = [int(e) for e in list(updatejamcoin(jamcoin, n))]

print(output)

# write to txt
with open(fileOutput, "w") as text_file:
    text_file.write(output)
