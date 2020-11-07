#!/usr/bin/env python
# coding: utf-8
"""
Name: Michael Austin

Academic Integrity Policy: The work I have submitted is my own and I have not done anything that would violate the academic integrity policy of WVU.
"""

# Works as far as I can tell. The result is just the sum of combinations using a looping structure.
# and combinations() works, so...
def hypercake(n,k):
    # takes int as variables 
    def combinations(n,r):
        # Factorial implemented in a recursive fashion. 
        def factorial(n):
            if n <= 1:
                return 1
            else:
                return (n * factorial(n-1))

        numerator = factorial(n)
        r_factorial = factorial(r)
        n_minus_r_factorial = factorial(n-r)
        result = (numerator/(r_factorial*n_minus_r_factorial))
        return int(result)

    result = 0
    for i in range(k):
       result += combinations(n,i)
    return int(result)

# Asks user for values of n and k, then passes them on to the hypercake function.
def display():
    print("Please enter a value for n number of cuts: ")
    n = int(input())
    print("Please enter a value for k dimensions: ")
    k = int(input())
    result = hypercake(n,k)
    print(f"The hypercake result of n = {n} and k = {k} is {result}")

def main():
    display()

if __name__ == "__main__":
    main()



