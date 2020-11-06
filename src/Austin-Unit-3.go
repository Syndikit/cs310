package main

import (
	"fmt"
)

func factorial(n int) int{
	var result = 1
	for i:=n; i >=1; i--{
		result *= i
	}
	return result
}

func combinations(n int, r int) int{
	var numerator = factorial(n)
    var rFactorial = factorial(r)
    var nMinusRFactorial = factorial(n-r)
    return (numerator/(rFactorial*nMinusRFactorial))
}

func pancake(n int) int{
	var n0 int = combinations(n,0)
    var n1 int = combinations(n,1)
	var n2 int = combinations(n,2)
    return n0 + n1 + n2
}

func cake(n int) int{
	var pan = pancake(n)
	var n3 = combinations(n,3)
	return pan + n3
}

func hypercake(n int) int{
	var result = cake(n)
	var counter = 3
	
    for i:= 4; i<n;i++{
		result += combinations(n,counter)
	}
	return result
}

   

func main(){
	fmt.Println(hypercake(7))
}