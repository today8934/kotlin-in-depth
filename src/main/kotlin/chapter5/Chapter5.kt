package chapter5

fun main() {
    println(sum(intArrayOf(1, 2, 3)))
    println(max(intArrayOf(1, 2, 3)))

    val lessThan: (Int, Int) -> Boolean = {a, b -> a < b}
    println(lessThan(1,11))

    val shifter: (Int) -> (Int) -> Int = { n -> { i -> i + n}} //함수 타입을 다른 함수 타입 안에 내포시켜서 고차 함수의 타입을 정의할 수 있다.
    val inc = shifter(1)
    val dec = shifter(-1)

    println(inc(10))
    println(dec(10))

    val evalAtZero: ((Int) -> (Int)) -> Int = { f -> f(0)} //Int를 받아서 Int를 내놓는 함수를 인자로 받아서 Int로 결과를 돌려주는 함수

    println(evalAtZero { n -> n + 1})
    println(evalAtZero { n -> n - 1})
}

/*
op를 함수처럼 호출할 수 있다.
람다식: 단순한 형태의 문법을 사용해 정의하는 이름이 없는 지역 함수.
 */
fun aggregate(numbers: IntArray, op: (Int, Int) -> Int): Int {
    var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty array")

    for (i in 1..numbers.lastIndex) result = op(result, numbers[i])

    return result
}

/*
result와 op는 함수 파라미터 역할을 하며 -> 다음에 오는 식은 결과를 계산하는 식이다.
 */
fun sum(numbers: IntArray) = aggregate(numbers) { result, op -> result + op }

fun max(numbers: IntArray) = aggregate(numbers) { result, op -> if (op > result) op else result}

/*
함수가 인자를 받지 않는 경우에는 함수 타입의 파라미터 목록에 빈 괄호를 사용한다.
 */
fun measureTime(action: () -> Unit): Long {
    val start = System.nanoTime()

    action()

    return System.nanoTime() - start
}

/*
다른 타입과 마찬가지로 함수 타입도 널이 될 수 있는 타입으로 지정할 수 있다.
 */
fun measureTime2(action: (() -> Unit)?): Long {
    val start = System.nanoTime()
    action?.invoke()
    return System.nanoTime() - start
}