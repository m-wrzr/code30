package main

import (
	"fmt"
	"os"
	"bufio"
	"log"
	"strconv"
)

func main() {

	file, err := os.Open("A-large-practice.in")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	scanner.Scan()

	T, err := strconv.Atoi(scanner.Text())
	fmt.Println(T)

	for i := 1; i <= T; i++ {
		scanner.Scan()
		fmt.Printf("Case #%d: %v\n", i, solve(scanner.Text()))
	}
}

func solve(phone_nr string) string {

	var nLetter = make(map[string]int)

	for _, l := range phone_nr {

		letter := string(l)

		val, key_there := nLetter[letter]

		if !key_there {
			nLetter[letter] = 1
		} else {
			nLetter[letter] = val + 1
		}
	}

	var nAmount = make(map[int]int)

	// help for big input from code jam analysis ;)
	// idea is to remove in a order where char is only used by this digit
	nLetter, nAmount[0] = removeDigit("Z", "ZERO", nLetter)
	nLetter, nAmount[6] = removeDigit("X", "SIX", nLetter)
	nLetter, nAmount[8] = removeDigit("G", "EIGHT", nLetter)
	nLetter, nAmount[2] = removeDigit("W", "TWO", nLetter)
	nLetter, nAmount[4] = removeDigit("U", "FOUR", nLetter)
	nLetter, nAmount[5] = removeDigit("F", "FIVE", nLetter)
	nLetter, nAmount[7] = removeDigit("V", "SEVEN", nLetter)
	nLetter, nAmount[3] = removeDigit("T", "THREE", nLetter)
	nLetter, nAmount[9] = removeDigit("I", "NINE", nLetter)
	nLetter, nAmount[1] = removeDigit("O", "ONE", nLetter)

	var result string = ""

	for i := 0; i < 10; i++ {

		if nAmount[i] > 0 {

			for j := 0; j < nAmount[i]; j++ {
				result += strconv.Itoa(i)
			}
		}
	}
	return result
}

func removeDigit(letter, full string, nLetter map[string]int) (map[string]int, int) {

	val, key_there := nLetter[letter]

	if key_there && val > 0 {

		for _, l := range full {
			one_l := string(l)
			nLetter[one_l] = nLetter[one_l] - val
		}
	}

	// fmt.Println(nLetter, val)
	return nLetter, val
}