package main

import (
	"os"
	"bufio"
	"strconv"
	"strings"
	"sort"
	"fmt"
)

func main() {

	inputSize := "large"

	input, err := os.Open("A-" + inputSize + "-practice.in")
	check(err)
	defer input.Close()

	scanner := bufio.NewScanner(input)
	scanner.Scan()

	T, _ := strconv.Atoi(scanner.Text())

	result := ""
	for i := 1; i <= T; i++ {

		scanner.Scan()
		tmp := strings.Split(string(scanner.Text()), " ")
		N, _ := strconv.Atoi(tmp[0])
		MAX, _ := strconv.Atoi(tmp[1])

		scanner.Scan()
		tmp = strings.Split(string(scanner.Text()), " ")

		var files = []int{}

		for _, file := range tmp {
			j, _ := strconv.Atoi(file)
			files = append(files, j)
		}
		sort.Ints(files)

		result += "Case #" + strconv.Itoa(i) + ": " + solve(N, MAX, files) + "\n"
	}
	fmt.Printf(result)

	output, err := os.Create("A-" + inputSize + "-output.txt")
	check(err)
	defer output.Close()

	output.WriteString(result)
	output.Sync()
}

/* greedily iterate over sorted array and remove first fitting */
func solve(N, MAX int, files []int) string {

	needed := 0

	for len(files) > 0 {
		head := files[0]
		files = append(files[:0], files[1:]...)

		for i := len(files) - 1; i >= 0; i-- {
			if head + files[i] <= MAX {
				files = append(files[:i], files[i + 1:]...)
				break
			}
		}
		needed++
	}
	return strconv.Itoa(needed)
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}