package com.Exercise

import com.Exercise.Exercise._
import org.scalatest.funspec.AnyFunSpec

class ExerciseTest extends AnyFunSpec {
  describe("characterCompare") {
    describe("When currentString < sortedString") {
      it ("should return -1") {
        assert(characterCompare("a", "b", 0, 0) === -1)
        assert(characterCompare("aaaax", "aaaay", 0, 0) === -1)
        assert(characterCompare("some aaaax", "other aaaay", 5, 6) === -1)
        assert(characterCompare("", "2", 0, 0) === -1)
      }
    }

    describe("When the string after the space in currentString is the first part of the string after the space in sortedString") {
      it("should return -1") {
        assert(characterCompare("s", "ss", 0, 0) === -1)
        assert(characterCompare("Hey Jude,", "Hey Jude, don't make it bad", 0, 0) === -1)
        assert(characterCompare("xyzone", "abconeandtwo", 3, 3) === -1)
      }
    }

    describe("When currentString > sortedString") {
      it("should return 1") {
        assert(characterCompare("e", "d", 0, 0) === 1)
        assert(characterCompare("hello there", "hello their", 0, 0) === 1)
        assert(characterCompare("hello-zz", "goodbye-aa", 6, 7) === 1)
      }
    }

    describe("When the string after the space in sortedString is the first part of the string after the space in current") {
      it("should return 0") {
        assert(characterCompare("I am", "I", 0, 0) === 0)
        assert(characterCompare("The quick brown fox jumps over the lazy dog", "The quick brown fox jumps over the lazy do", 0, 0) === 0)
        assert(characterCompare("here+g", "there+", 0, 1) === 0)
        assert(characterCompare("1", "", 0, 0) === 0)
      }
    }
  }

  describe("insertString") {
    describe("When string already present in sortedUntested") {
      it("return sortedTested concatenated with sortedUntested") {
        assert(insertString("five", Vector.empty[String], Vector("five")) === Vector("five"))
      }
    }

    describe("When currentString is less than an element in sortedUntested") {
      it("should insert before the element it is less than") {
        assert(insertString("good dog", Vector.empty[String], Vector("bad man")) === Vector("good dog", "bad man"))
        assert(insertString("bob dylan", Vector.empty[String], Vector("rolling stone", "tambourine man")) === Vector("bob dylan", "rolling stone", "tambourine man"))
        assert(insertString("cheese pizza", Vector.empty[String], Vector("pepperoni pizza", "mushroom pizza", "large soda", "chicken wings")) === Vector("pepperoni pizza", "mushroom pizza", "cheese pizza", "large soda", "chicken wings"))
      }
    }

    describe("When currentString is greater than an element in sortedStrings") {
      it("should insert at the end of sortedStrings") {
        assert(insertString(" ab", Vector.empty[String], Vector(" aa")) === Vector(" aa", " ab"))
        assert(insertString("fruit punchz", Vector.empty[String], Vector("Hawaiian puncha", "someother puncht")) === Vector("Hawaiian puncha", "someother puncht", "fruit punchz"))
        assert(insertString("San Diego", Vector.empty[String], Vector("Pan Diega", "Dan Diegy")) === Vector("Pan Diega", "Dan Diegy", "San Diego"))
        assert(insertString("chocolate cake", Vector.empty[String], Vector("cheese cake", "tiramisu cake")) === Vector("cheese cake", "tiramisu cake", "chocolate cake"))
      }
    }
  }

  describe("orderVaguelyAfterSpace") {
    // empty Vector
    describe("When given an empty Vector") {
      it("should return an empty Vector") {
        assert(sortVaguelyAfterSpace(Vector()) === Vector())
      }
    }

    describe("When Vector contains strings without spaces") {
      it("should return the Vector but exclude strings without spaces") {
        assert(sortVaguelyAfterSpace(Vector("one", "two", "three", "four")) === Vector())
        assert(sortVaguelyAfterSpace(Vector("beef", "pork", "silken tofu", "chicken")) === Vector("silken tofu"))
        assert(sortVaguelyAfterSpace(Vector("two", " 2", "one", " 1" )) === Vector(" 2", " 1"))
      }
    }

    describe("When the Vector has duplicate strings") {
      it("should exclude any duplicates") {
        assert(sortVaguelyAfterSpace(Vector("hey {", "hey {")) === Vector("hey {"))
      }
    }

    describe("When the Vector is already properly ordered") {
      it("should return the Vector as is") {
        assert(sortVaguelyAfterSpace(Vector("e e", "d d", "c c", "b b", "a a")) === Vector("e e", "d d", "c c", "b b", "a a"))
      }
    }

    describe("When Vector is unsorted and has mix of strings with and without spaces") {
      it("should return sorted Vector but exclude strings without spaces") {
        assert(sortVaguelyAfterSpace(Vector(" red", "orange orange", "yellow", "x green x", "bright blue", "indigo", "ultra violet")) === Vector(" red", "orange orange", "x green x", "ultra violet", "bright blue"))
        assert(sortVaguelyAfterSpace(Vector("circle circle", "triangle triangle", "square square")) === Vector("triangle triangle", "square square", "circle circle"))
        assert(sortVaguelyAfterSpace(Vector(" sunny", "c cloudy", "snowy", "very rainy")) === Vector(" sunny", "very rainy", "c cloudy"))
        assert(sortVaguelyAfterSpace(Vector("KJ>72k;l ASDS8972 ||", "", "ahj HAJKSD", "ajklaes897'2;2çn82", "kljah ", "(#&&9023974 3875237")) === Vector("KJ>72k;l ASDS8972 ||", "ahj HAJKSD", "(#&&9023974 3875237", "kljah "))
      }
    }
  }
}
