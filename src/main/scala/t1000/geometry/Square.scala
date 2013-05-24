package t1000.geometry


class Square[T] (values: Seq[T])(implicit m: ClassManifest[T]) {
  val size = math.sqrt(values.length).toInt
  val center = Coordinates.Absolute.Point(size / 2, size / 2)

  def contains(point: Coordinates#Point) = {
    val abs = point.absolute
    0 <= abs.x && abs.x < size && 0 <= abs.y && abs.y < size
  }

  def contentAt(point: Coordinates#Point) =
    if (contains(point)) Some(valueArray(point.absolute.y)(point.absolute.x))
    else None

  def modify(point: Coordinates#Point, modification: T => T) {
    val current = contentAt(point).get
    valueArray(point.absolute.y)(point.absolute.x) = modification(current)
  }

  override def toString = {
    val longest = valueArray.flatMap(row => row).map(_.toString.length).max
    val result = valueArray.map(row =>
      row.map(cell =>
        cell.toString.padTo(longest, " ").mkString)
        .mkString(" "))
      .mkString("\n")

    result
  }

  private val valueArray = values
    .grouped(size)
    .map(_.toArray(m))
    .toArray
}
