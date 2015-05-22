package edu.arizona.sista.struct

/**
 *  An interval of integers.
 *
 *  @constructor create a new interval
 *  @param start the first element of the interval
 *  @param end the last element of the interval (exclusive)
 */
class Interval(val start: Int, val end: Int) extends IndexedSeq[Int] with Ordered[Interval] {
  require(start < end || (start == 0 && end == 0), "invalid range")

  import Interval.empty

  override def toString: String = s"Interval($start, $end)"

  def length: Int = end - start

  def contains(i: Int): Boolean = i >= start && i < end

  def apply(index: Int): Int = {
    require(index >= 0 && index < length, "invalid index")
    start + index
  }

  def min: Int = start

  def max: Int = end - 1

  def compare(that: Interval): Int =
    if (this.start > that.start) 1
    else if (this.start < that.start) -1
    else this.size - that.size

  /** returns true if the given interval is contained by this interval */
  def contains(that: Interval): Boolean =
    this.start <= that.start && this.end >= that.end

  /** returns true if there is any overlap between the members of the intervals */
  def overlaps(that: Interval): Boolean =
    if (this == empty) false
    else if (that == emtpy) false
    else if (this.start < that.start) {
      this.end > that.start
    } else if (this.start > that.start) {
      this.start < that.end
    } else true
}

private[struct] object Empty extends Interval(0, 0) {
  override def toString: String = "Empty"
}

object Interval {
  /** the empty interval */
  val empty: Interval = Empty
  /** make an interval with a single element */
  def apply(i: Int): Interval = new Interval(i, i + 1)
  def apply(start: Int, end: Int): Interval = new Interval(start, end)
}
