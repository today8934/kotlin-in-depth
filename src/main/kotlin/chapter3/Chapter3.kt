package chapter3

import kotlin.math.PI
import kotlin.random.Random

class Chapter3 {

    /*
    코틀린 표준 상수 PI를 사용
     */
    fun circleArea(radius: Double): Double {
        return PI*radius*radius
    }

    /*
    함수 파라미터가 참조(Call by Reference 라면 함수 외부에서 파라미터로 준 객체여도 값이 변한다.(기본적으로 Call by Value임)
     */
    fun increment(a: IntArray): Int {
        return ++a[0]
    }

    /*
    Unit 타입은 자바의 void 에 해당하는 코틀린 타입으로, 함수 리턴이 없는경우 Unit 을 생략해도 코틀린 컴파일러가 Unit 함수를 정의한다고 가정한다.
     */
    fun prompt(name: String): Unit {
        println("***** Hello, $name! *****")
    }

    /*
    식이 본문인(expression-body) 함수.
    단일 식으로만 함수가 구성될 수 있으면, return 키워드와 {}를 생략할 수 있다.
    반환 타입도 생략가능(타입 추론)
     */
    fun circleArea2(radius: Double): Double = PI*radius*radius

    fun rectangleArea(width: Double, height: Double): Double = width*height

    fun swap(s: String, from: Int, to: Int): String {
        val chars = s.toCharArray()
        val tmp = chars[from]
        chars[from] = chars[to]
        chars[to] = tmp
        return chars.concatToString()
    }

    fun mul(a: Int, b: Int) = a*b // 1
    fun mul(a: Int, b: Int, c: Int) = a*b*c // 2
    fun mul(s: String, n: Int) = s.repeat(n) // 3
    fun mul(o: Any, n: Int) = Array(n) { o } // 4

    fun restrictToRange(
        from: Int = Int.MIN_VALUE,
        to: Int = Int.MAX_VALUE,
        what: Int
    ): Int = from.coerceAtLeast(to.coerceAtMost(what))

    fun printSorted(vararg items: Int) {
        items.sort()
        println(items.contentToString())
    }

    fun max(a: Int, b: Int) = if (a > b) a else b

    fun renamePackage(fullName: String, newName: String): String {
        val i = fullName.lastIndexOf('.')
        val prefix = if (i >= 0) fullName.substring(0, i + 1) else return newName
        return prefix + newName
    }

    fun range() {
        val chars = 'a' .. 'h'
        val twoDigits = 10..99
        val zero2One = 0.0..1.0

        1..10 step 3
        15 downTo 9 step 2

        val arr = IntArray(10) { it }.sliceArray(2..5)

        println(arr.contentToString())
    }

    fun hexDigit(n: Int): Char {
        return when (n) {
            in 0..9 -> '0' + n
            in 10..15 -> 'A' + n - 10
            else -> '?'
        }
    }

    fun doWhileStatement() {
        var sum = 0
        var num = 0

        do {
            num = readLine()!!.toInt()
            sum += sum
        } while (num != 0)

        println("Sum: $sum")
    }

    fun whileStatement() {
        val num = Random.nextInt(1, 101)
        var guess = 0

        while (guess != num) {
            guess = readLine()!!.toInt()
            if (guess < num) println("Too small")
            else if (guess > num) println("Too big")
        }
        println("Right: it's $num")
    }

    fun forStatement() {
        val a = IntArray(10) {it*it}
        var sum = 0

        for (x in a) {
            sum+= x
        }

        println("Sum: $sum")

        for (i in 0..a.lastIndex) {
            println(i)
        }

        for (i in 0..a.lastIndex step 2) {
            println(i)
        }
    }

    fun parseIntNumber(s: String, fallback: Int = -1): Int {
        var num = 0

        if (s.length !in 1..31) return fallback

        for (c in s) {
            if (c !in '0'..'1') return fallback
            num = num*2 + (c - '0')
        }
        return num
    }

    fun indexOf(subarray: IntArray, array: IntArray): Int {
        outerLoop@ for (i in array.indices) {
            for (j in subarray.indices) {
                if (subarray[j] != array[i + j]) continue@outerLoop
            }
            return i
        }
        return -1
    }

    tailrec fun binIndexOf(
        x: Int,
        array: IntArray,
        from: Int = 0,
        to: Int = array.size
    ): Int {
        if (from == to) return -1
        val midIndex = (from + to - 1) / 2
        val mid = array[midIndex]
        return when {
            mid < x -> binIndexOf(x, array, midIndex + 1, to)
            mid > x -> binIndexOf(x, array, from, midIndex)
            else -> midIndex
        }
    }
}

fun main() {
    val chapter3 = Chapter3()
    println(chapter3.circleArea(2.0))

    val a = intArrayOf(1, 2, 3)
    println(chapter3.increment(a))
    println(chapter3.increment(a))

    chapter3.prompt("Wooksang")

    println(chapter3.circleArea2(2.5))

    val w = readLine()!!.toDouble()
    val h = readLine()!!.toDouble()
    println(chapter3.rectangleArea(width = w, height = h)) // named argument
    println(chapter3.rectangleArea(height = h, width = w)) // 이런식으로도 호출 가능하다

    println(chapter3.swap("Hello", 1, 2))
    println(chapter3.swap("Hello", from = 1, to = 2))
    println(chapter3.swap("Hello", to = 3, from = 0))
    println(chapter3.swap("Hello", 1, to = 3))
    println(chapter3.swap(from = 1, s = "Hello", to = 2))
    println(chapter3.swap(s = "Hello", 1, 2))
    println(chapter3.swap(s = "Hello", 1, to = 2))

    chapter3.mul(1, 2) // Int가 Any의 하위 타입이므로 1과 4중에 1을 선택
    //chapter3.mul(1, 2L) // (Int, Long)타입을 받을 수 있는 함수가 없으므로 오류
    chapter3.mul(1L, 2) // (Long, Int) 타입을 받을 수 있는 함수는 4번
    chapter3.mul("0", 3) // String이 Any의 하위 타입이기 때문에 3과 4중에 3을 선택
    chapter3.mul("0" as Any, 3) // (Any, Int)를 받을 수 있는 함수는 4뿐이므로 4를 선택

    println(chapter3.restrictToRange(10, 20, 5))
    chapter3.printSorted(6, 2, 10, 1)

    val numbers = intArrayOf(6, 2, 10, 1)
    chapter3.printSorted(*numbers) // * 연산자를 사용하면 배열을 가변 인자 대신 넘길 수 있다.
    //chapter3.printSorted(numbers)

    val b = intArrayOf(6, 2, 10, 1)
    chapter3.printSorted(*b)
    println(b.contentToString()) // 스프레드 연산자(*)는 배열을 복사해서 넘기므로 원본 배열에는 영향을 미치지 않는다.

    /*
    둘 이상을 vararg 파라미터로 선언하는 것은 금지된다.
    하지만 vararg 파라미터에 콤마로 분리한 여러 인자와 스프레드를 섞어서 전달하는 것은 괜찮음.
     */
    chapter3.printSorted(6, 1, *intArrayOf(3, 8), 2)

    println(chapter3.max(10, 15))

    /*
    코틀린은 자바와 달리 삼항연산자(조건 ? 참일때 : 거짓일때)가 없다. 대신 이를 if문으로 쓸 수 있다.
     */
    val s = readLine()!!
    val i = s.indexOf("/")
    val result = if (i >= 0) {
        val a = s.substring(0, i).toInt()
        val b = s.substring(i + 1).toInt()
        (a/b).toString()
    } else ""

    println(result)

    println(chapter3.renamePackage("foo.bar.old", "new"))

    chapter3.range()

    /*
    break: 루프를 종료시키고, 루프 바로 다음 문으로 실행흐름을 이동시킨다
    continue: 현재 루프 이터레이션을 마치고 조건검사로 이동한다.
     */
    chapter3.forStatement()

    val array = IntArray(11) {it*it}

    println(chapter3.binIndexOf(16, array, 0))
}