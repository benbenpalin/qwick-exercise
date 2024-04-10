# Qwick Coding Exercise - String Sorter

## Description

For the coding exercise, I was asked to rewrite the provided [javascript code](./docs/original-exercise.js) in another language, using only pure functions. The original code reverse sorts a collection of strings based on the characters integers after the first space in each string, and excludes any strings without spaces. It does this using a version of the insertion sort algorithm.

In this repo, I have rewritten the code in Scala, using only pure functions and immutable data structures, the [Exercise](./src/main/scala/com/Exercise/Exercise.scala) object

### Sorting method
Here is how it sorts the strings that contain spaces:

* Inserts the first string into a Vector of results. This vector will always be properly sorted.
* Each additional string is compared to the first element of the sorted vector. The strings are compared, character by character, starting with the character after the first space.
  * If the character of the string to be inserted has a lower character integer than the one it is being compared to, the string is inserted in the results before the compared string.
  * If the character of the string to be inserted has a higher character integer than the one it is being compared to, the string is inserted at the end of the result vector.
  * If the character integers are the same, we move on to the next character of both strings.
  * If we reach the end of the comparison string, we move on to the next string in the results.
  * If we reach the end of the current string, we consider the string to be less than, and we insert it before the comparison string.

### Flaw in algorithm
While I implemented the code to get the exact same results as the original javascript code, it is not an accurate implementation of the insertion sort method. In fact, as you can see in the test results, the return value is often not sorted by the characters after the string. This is because, when it determines that a string is greater than a string in the results, we always insert it at the end. In order to correct it, one would need to move on to the next string and compare it to that, instead. This would result in an accurate sort.

## Getting Started
For ease, download and install SDKMAN! to easily download the rest of the dependencies
https://sdkman.io/install

Download and install java (Amazon Coretto, 17.0.9)
```
sdk install 17.0.9-amzn
```
Answer `Do you want java 17.0.9-amzn to be set as default` with `Y`

Download and install sbt (1.9.9)
```
sdk install sbt 1.9.9
```
Answer `Do you want sbt 1.9.9 to be set as default` with `Y`

Download scala (2.13.3)
```
sdk install scala 2.13.3
```
Answer `Do you want scala 2.13.3 to be set as default` with `Y`

### Running Tests
To run the unit tests, go into the directory of the program and run:
```
sbt test
```
