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

    println(check("Hello") { c -> c.isLetter() })
    println(check("Hello") { it.isLowerCase() }) //람다 인자가 하나인 경우에는 파라미터 목록과 화살표 기호를 생략하고 it라는 이름을 사용하여 기리킬 수 있다.

    println(check2("Hello") { _, c -> c.isLetter() }) //사용하지 않는 람다 파라미터를 _로 지정할 수 있다.
    println(check2("Hello") { i, c -> i == 0 || c.isLowerCase() })

    println(check3("Hello") { c -> isCapitalLetter(c) })
    println(check3("Hello") { isCapitalLetter(it) })
    println(check3("Hello", ::isCapitalLetter)) //호출 가능 참조: ::isCapitalLetter 이 식이 가리키는 isCapitalLetter() 함수와 같은 동작을 하는 함수값을 표현해준다.

    println(evalAtZero(::inc))
    println(evalAtZero(::dec))

    /*
    바인딩된 호출 가능 참조(bound callable reference)
    멤버 함수를 호출할 수 있다.
     */
    val isJohn = Person("John", "Doe")::hasNameOf
    println(isJohn("JOHN"))
    println(isJohn("Jake"))

    val person = Person("John", "Doe")
    //val readName = person::firstName.getter
    //val writeFamily = person::familyName.setter

    //println(readName)
    //writeFamily("Smith")
    println(person.familyName)

    println(indexOf(intArrayOf(4, 3, 2, 1)) { it < 3})

    println("Hello".truncate(3))
}

fun String.truncate(maxLength: Int): String {
    return if (length <= maxLength) this else substring(0, maxLength)
}

/*
indexOf 함수에서 원래대로라면 condition 함수 객체를 넘겨받을 때 항상 새로운 객체를 생성한다.
이를 최적화 시켜줄 수 있는 키워드가 inline 키워드이다.
인라인 키워드를 사용하면 함수를 넘겨받을 때 마다 객체를 새로 생성하지 않고 넘겨받는 함수가 함수의 내부에 픽스되게 된다.
 */
inline fun indexOf(numbers: IntArray, condition: (Int) -> Boolean): Int {
    for (i in numbers.indices) {
        if (condition(numbers[i])) return i
    }

    return -1
}

/*
null이 될 수 있는 타입의 인자를 받을 수 없다.
이런경우 특정 람다를 인라인하지 말라고 파라미터 앞에 noinline 키워드를 붙일 수 있다.
 */
inline fun forEach2(a: IntArray, noinline  action: ((Int) -> Unit)?) {
    if (action == null) return
    for (n in a) action(n)
}

class Person(var firstName: String, var familyName: String) {
    fun hasNameOf(name: String) = name.equals(firstName, ignoreCase = true)
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
fun sum(numbers: IntArray) = aggregate(numbers) { result, op -> result + op } //람다식(파라미터: result, op 본문: result + op)

fun max(numbers: IntArray) = aggregate(numbers) { result, op -> if (op > result) op else result}

/*
익명 함수
- 익명 함수에는 이름을 지정하지 않는다. fun키워드 다음에 바로 파라미터 목록이 온다.
- 람다와 마찬가지로 문맥에서 파라미터 타입을 추론할 수 있으면 파라미터 타입을 지정하지 않아도 된다.
함수 정의와 달리, 익명 함수는 식이기 때문에 인자로 함수에 넘기거나 변수에 대입하는 등 일반 값처럼 쓸 수 있다.
 */
fun sum2(numbers: IntArray) = aggregate(numbers, fun(result, op) = result + op)

/*
익명함수 본문이 블록인 경우 명시적으로 반환 타입을 지정해야 한다.
 */
fun sum3(numbers: IntArray) = aggregate(numbers, fun(result, op): Int {return result + op})

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

fun check(s: String, condition: (Char) -> Boolean): Boolean {
    for (c in s) {
        if (!condition(c)) return false
    }
    return true
}

fun check2(s: String, condition: (Int, Char) -> Boolean): Boolean {
    for (i in s.indices) {
        if (!condition(i, s[i])) return false
    }
    return true
}

/*
람다나 익명 함수도 클로저 또는 자신을 포함하는 외부 선언에 정의된 변수에 접근할 수 있다.
특히 람다나 익명 함수도 외부 영역의 가변 변수 값을 변경할 수 있다.
반면 자바 람다는 외부 변수의 값을 변경할 수 없다.
 */
fun forEach(a: IntArray, action: (Int) -> Unit) {
    for (n in a) {
        action(n)
    }
}

fun check3(s: String, condition: (Char) -> Boolean): Boolean {
    for (c in s) {
        if (!condition(c)) return false
    }
    return true
}

fun isCapitalLetter(c: Char) = c.isUpperCase() && c.isLetter()
fun evalAtZero(f: (Int) -> Int) = f(0)
fun inc(n: Int) = n + 1
fun dec(n: Int) = n - 1