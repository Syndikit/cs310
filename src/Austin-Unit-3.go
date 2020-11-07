/* Name: Michael Austin
* Academic integrity statement: I complied with all academic integrity policies * of WVU and have not cheated. 
*
*/
package main

import (
	"fmt"
)

func hypercake(n int, k int) int{

	combinations := func(x int, r int) int{

		var factorial func(m int) int
		factorial = func(m int) int{
			if m==1 || m ==0 {
				return 1
			}
			return m*factorial(m-1)	
		}
		var numerator = factorial(x)
    	var rFactorial = factorial(r)
    	var nMinusRFactorial = factorial(x-r)
    	return (numerator/(rFactorial * nMinusRFactorial))
	}
	var result int = 0
	for i:=0; i<=k;i++{
		result += combinations(n,i)
	}
	return result
}

func display(){
	var n int
	var k int
	fmt.Println("Please enter a value for n number of cuts: ")
	fmt.Scanln(&n)
	fmt.Println("Please enter a value for k dimensions: ")
	fmt.Scanln(&k)
	var result = hypercake(n,k)
	var out = fmt.Sprintf("The hypercake result of n = %d and k = %d is: %d",n,k,result)
	fmt.Println(out)
}
   

func main(){
	display()
}