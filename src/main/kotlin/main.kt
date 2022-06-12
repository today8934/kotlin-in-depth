import java.util.*

/*
JVM 진입점은 반드시 정적 클래스 메서드(static class method)여야 하지만,
여기서 클래스를 정의하지 않고도 실행될 수 있는 이유는 코틀린 컴파일러가 자동으로
클래스를 하나 만들고 main 메서드를 호출하는 진입점을 넣어줬기 때문이다.
 */
fun main() {
    println("Hello, KotlinVerse!")

    /*
    !!은 널 아님 단언(not-null assertion)으로, readLine()의 결과가 null인 경우 예외를 발생시킨다.
    널이 아닌것이 확실하지 않은 값에 대해 toInt()함수를 호출하게 막음으로써 널로인한 오류를 방지한다.
     */
    val a = readLine()!!.toInt() //타입 추론을 통하여 변수 타입을 지정하지 않았는데도 문맥에서 타입을 도출해준다.
    val b = readLine()!!.toInt()
    println(a + b)

    var n: Int = 100
    var text: String = "Hello!"

    val text2: String
    text = "Hello!"

    var sum = 1
    sum = sum + 2
    sum = sum + 3

    println(sum)

    var result = 3
    result *= 10
    result += 6

    println(result)

    var aa = 1
    println(aa++)
    println(++aa)
    println(--aa)
    println(aa--)

    println(1.5.toInt()) // 1.5라는 Double타입에 정의된 toInt를 호출한다.(Int와 같이 원시 타입과 비슷한 타입들도 메서드와 프로퍼티를 제공함)

    val nn: Any = 1 //널을 허용하지 않는 모든 코틀린 타입은 Any라는 타입의 하위타입이다.
    println(nn)

    val integer = 34_721_189 //큰 수를 나타낼 때 _를 이용하여 가독성을 높일 수 있다
    println(integer)

    val hundredLong = 100L //L이나 l을 붙이면 Long타입이 된다.
    val bin = 0b10101 // 0b를 붙이면 2진수가 된다.
    val hex = 0xF9 // 0x를 붙이면 16진수가 된다.

    /*
    각 정수 타입에는 최솟값, 최댓값에 대한 상수 정의가 들어있다.
     */
    println(Short.MIN_VALUE)
    println(Short.MAX_VALUE)
    println(Int.MIN_VALUE)
    println(Int.MAX_VALUE)
    println(Long.MIN_VALUE)
    println(Long.MAX_VALUE)

    /*
    자바와 마찬가지로 부동소수점(Float, Double)을 지원한다.
     */
    val pi = 3.14f //디폴트는 Double이며, f나 F를 붙이면 float이 된다.
    val one = 1.0
    val quarter = .25

    // Double > Float > Long > Int > Short > Byte
    /*
    byte + byte = 2:Byte
    int + byte = 2: Int
    int + int = 2: Int
    int + long = 2: Long
    long + double = 2.5: Double
    float + double = 3.0: Double
    float + int = 2.5: Float
    long + double = 2.5: Double
     */

    val name = readLine()
    println("Hello, $name!\nToday is ${Date()}")

    var message = """Hello, $name!
        Today is ${Date()}
    """.trimIndent()
    println(message)

    /*
    자바의 ==와 =! 연산자는 참조 동등성을 비교하기 때문에 실제 문자열 내용을 비교하려면 equals() 메서드를 사용해야한다.
    코틀린에서는 ==가 기본적으로 equals()를 가리키는 편의 문법이기 때문에 따로 equals()를 호출할 필요가 없다.
    코틀린에서 참조 동등성을 비교하려면 ===, !==연산자를 사용하면 된다.
     */
    val s1 = "Hello!"
    val s2 = "Hel" + "lo!"
    println(s1 == s2)
    println(s1 === s2)

    val aaa = emptyArray<String>()
    val bbb = arrayOf("hello", "world")
    val ccc = arrayOf(1, 4, 9)

    val size = readLine()!!.toInt()
    val squares = Array(size) {//중괄호 안의 요소를 람다(lamda)라고 부른다. 람다는 인덱스를 기반으로 값을 계산하는 식을 정의한다.
        (it + 1)*(it + 1)
    }

    for(i in 1 .. squares.size) {
        println(squares[i - 1])
    }

    val operations = charArrayOf('+', '-', '*', '/', '%')
    val intSquares = IntArray(10) { (it + 1)*(it + 1)}

    intSquares.iterator().forEach { it -> println(it)}

    val numbers = squares
    numbers[0] = 1000
    println(squares[0]) // 바뀐 데이터가 numbers와 squares에 공유된다. 원본과 별도의 배열을 만들고 싶다면 copyOf를 사용하여야 함

    val bbbb = intArrayOf(1, 2, 3) + 4
    val cccc = intArrayOf(1, 2, 3) + intArrayOf(5, 6) + 7 + 8

    bbbb.iterator().forEach { it -> println(it) }
    cccc.iterator().forEach { it -> println(it) }

    val ab = intArrayOf(1, 2, 3)
    val bc = intArrayOf(1, 2, 3)

    /*
    배열간 비교는 String과 달리 참조 동등성 비교이다.
    배열내용을 비교하려면 contentEquals()를 사용한다.
     */
    println(ab == bc)
    println(ab === bc)
    println(ab.contentEquals(bc))

}