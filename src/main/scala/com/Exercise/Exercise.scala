package com.Exercise

import scala.annotation.tailrec

object Exercise extends App {
  @tailrec
  /** Compares currentString and sortedString, using tail recursion, by comparing the character Integers of each string,
   * starting at the indexes of currentStart and sortedStart, respectively. It the characters at the indexes are the
   * same, it moves on the the next character of each string until either the pair of character are not the same or it
   * has reached the end of one of the strings. The comparison rules are based on the rules of the original exercise's
   * javascript code located at '../../../docs/original-exercise.js'.
   *
   *
   * @param currentString String to be inserted into the sortedString Vector
   * @param sortedString previously sorted String
   * @param currentStart index of currentString character being compared
   * @param sortedStart index of sortedString character being compared
   * @return comparison integers representing the following:
   *         - 1:  currentString is greater than sortedString.
   *         - -1: currentString is less than sortedString.
   *         - 0:  currentString is equal to sortedString.
   */
  def characterCompare(
    currentString: String,
    sortedString: String,
    currentStart: Int,
    sortedStart: Int
  ): Int = {
    if (sortedStart == sortedString.length)
      0
    else if (currentStart >= currentString.length || currentString(currentStart) < sortedString(sortedStart))
      -1
    else if (currentString(currentStart) > sortedString(sortedStart))
      1
    else // the two characters are the same
      characterCompare(currentString, sortedString, currentStart + 1, sortedStart + 1)
  }

  @tailrec
  /** Inserts currentString into sortedStrings, depending on the return value from [[characterCompare]], using tail
   * recursion. In the params, sortedString is broken into two vectors, sortedTested and sortedUntested. Starting with
   * the leftmost string of sortedTested, compares it to currentString, and inserts currentString according to the
   * rules of original exercise's javascript code located at '../../../docs/original-exercise.js'.
   * The rules are as follows:
   * - If currentString is less than the sortedString, it is inserted between sortedTested and sortedUntested.
   * - If currentString is greater than the sortedString, it is inserted at the end of all sorted strings.
   * - If the current string is equal to the sortedString, it is compared to the next string in sortedUntested.
   *   - Unless there are no additional strings in sortedUntested. In that case, currentString is inserted at the
   *   end of all sortedStrings.
   *
   * @param currentString String to be inserted into the sortedString Vector
   * @param sortedTested Vector of strings we have already tested
   * @param sortedUntested Vector of strings yet to be tested
   * @return a Vector of strings with the currentString inserted in the correct place
   */
  def insertString(
    currentString: String,
    sortedTested: Vector[String],
    sortedUntested: Vector[String]
  ): Vector[String] = {
    val sortedString = sortedUntested.head

    if (sortedString == currentString) {
      sortedTested ++ sortedUntested // do not add currentString if it has already been sorted
    } else {
      characterCompare(
        currentString,
        sortedString,
        currentString.indexOf(" ") + 1,
        sortedString.indexOf(" ") + 1
      ) match {
        case -1 => (sortedTested :+ currentString) ++ sortedUntested
        case 1 => sortedTested ++ sortedUntested :+ currentString
        case 0 =>
          if (sortedUntested.tail == Nil)
            sortedTested ++ sortedUntested :+ currentString
          else
            insertString(currentString, sortedTested :+ sortedString, sortedUntested.tail)
      }
    }
  }

  /** Sorts a Vector of strings based on the logic in the original exercise's javascript code located at
   * '../../../docs/original-exercise.js'
   *
   * @param strings Vector of Strings to be sorted
   * @return Vector of all elements of strings param that contain spaces, sorted in the reverse order determined
   *         by [[insertString]]
   */
  def sortVaguelyAfterSpace(strings: Vector[String]): Vector[String] = {
    strings.foldRight(Vector.empty[String])((currentString, sortedStrings) => {
      if (!currentString.contains(" "))
          sortedStrings // Don't include strings without spaces
      else if (sortedStrings.isEmpty)
          sortedStrings :+ currentString // insert first element of sortedStrings
      else
        insertString(currentString, Vector.empty[String], sortedStrings)
    }).reverse
  }
}
