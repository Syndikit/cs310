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
/*
func pancake(n int){
	var n0 int = combinations(n,0)
    var n1 int = combinations(n,1)
	var n2 int = combinations(n,2)
	var res1 int = n0 + n1
	var res2 = 0
	res2 = res1 + n2
    return res2

}
   */ 

func main(){
	fmt.Println(pancake(7))
}