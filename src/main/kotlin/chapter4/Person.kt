package chapter4

import java.io.File

class Person(val fullName: String /* 주생성자 파라미터*/) {
    /*
    주생성자 파라미터에서 val 이나 var 키워드를 사용하면 클래스 멤버변수 선언을 사용하지 않을 수도 있다.(권장)
     */
    private val firstName: String
    private val familyName: String

    /*
    init 블록에는 return 문을 사용할 수 없다.
    주생성자 파라미터는 init 블록 밖이나 프로퍼티 초기화 외에는 사용할 수 없다.
     */
    init {
        val names = fullName.split(" ")

        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }

    fun main() {
        println("fullName: $fullName")
    }

}

fun main() {
    val person5 = Person5("John", "Doe")

    //println(person5.firstName) Person5 클래스에서 firstName과 familyName은 private이다.
    //println(person5.familyName)
    println(person5.fullName())

    //val empty = Empty() Empty 클래스의 주생성사자 private이기때문에 init할 수 없다.

    val id = Person6.Id("John", "Doe")
    val person6 = Person6(id, 25)
    person6.showMe()

    var name: String? = null

    fun initialize() {
        name = "John"
    }

    fun sayHello() {
        println(name!!.uppercase())
    }

    initialize()
    sayHello()
}

fun sayHello(name: String?) {
    println("Hello, " + (name ?: "Unknown")) //Elvis 연산자. name이 널이 아닐 경우에는 name, 널일경우 Unknown이 리턴된다.
}

class Name(val firstName: String, val familyName: String?)

class Person8(val name: Name?) {
    fun describe(): String {
        val currentName = name ?: return "Unknown"
        return "${currentName.firstName} ${currentName.familyName}"
    }
}

class Room(vararg val persons: Person /* 함수와 마찬가지로 디폴트 값과 vararg 를 생성자 파라미터에 사용할 수 있다. */) {
    fun showNames() {
        for (person in persons) println(person.fullName)
    }
}

class Person2 {
    val firstName: String
    val familyName: String

    constructor(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }

    constructor(fullName: String) {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}

class Person3 {
    val fullName: String

    constructor(firstName: String, familyName: String): this("$firstName $familyName") //생성자 위임 호출

    constructor(fullName: String) {
        this.fullName = fullName
    }
}

class Person4(val fullName: String) { /* Person3의 두번째 생성자를 주생성자처럼 쓸수도 있다. */
    constructor(firstName: String, familyName: String): this("$firstName $familyName")
}

/*
다음 코드에서 firstName과 familyName은 private이므로 main() 함수에서 이 둘을 볼 수 없다. 반면 fullName()은 public이다.
 */
class Person5(
    private val firstName: String,
    private val familyName: String
    ) {
    fun fullName() = "$firstName $familyName"
}

/*
주생성자의 가시성을 지정하려면 constructor 키워드를 꼭 명시해야 한다.
 */
class Empty private constructor() {
    fun showMe() = println("Empty")
}

class Person6(val id: Id, val age: Int) {
    class Id(val firstName: String, val familyName: String)
    fun showMe() = println("${id.firstName} ${id.familyName}, $age")
}

class Person7(private val id: Id, private val age: Int) {
    class Id(private val firstName: String, private val familyName: String) {
        fun nameSake(person: Person7) = person.id.firstName == firstName
    }
}

/*
코틀린의 모든 참조타입은 기본적으로 null이 될 수 없다.
코틀린에서 널이 될 수 있는 값을 받으려면, 파라미터 선언 시 파라미터 타입 뒤에 물음표(?)를 붙여서 널이 될 수 있는 타입임을 명시해야한다.
 */
fun isLetterString(s: String): Boolean {
    if (s.isEmpty()) return false
    for (ch in s) {
        if (!ch.isLetter()) return false
    }
    return true
}

fun isBooleanString(s: String?) = s == "false" || s == "true"

fun nullableToNotNullable() {
    val s: String? = "abc"
    val ss: String

    //ss변수에 s를 넣으면 컴파일 오류가 발생한다. not nullable 타입에 nullable 타입을 넣을 수 없기 때문
}

/*
스마트캐스트
 */
fun describeNumber(n: Int?) = when (n) {
    null -> "null"
    in 0..10 -> "small"
    in 11..100 -> "large"
    else -> "out of range"
}

/*
스마트캐스트
 */
fun isSingleChar(s: String?) = s != null && s.length ==1

class Content {
    /*
    lateinit 표시가 붙은 프로퍼티는 값을 읽으려고 시도할 때
    프로그램이 프로퍼티가 초기화됐는지 검사해서 초기화되지 않은 경우
    UninitializedPropertyAccessException을 던진다.
    lateinit을 사용하기 위해서는 변수를 var로 선언해야 하며
    Int나 Boolean 같은 원시 값을 표현하는 타입이 아니어야 한다.
     */
    lateinit var text: String

    fun loadFile(file: File) {
        text = file.readText()
    }
}

fun getContentSize(content: Content) = content.text.length

class Person9(var firstName: String, var familyName: String) {
    /*
    커스텀 게터는 프로퍼티를 읽으면 자동으로 get 함수를 호출한다.
     */
    var fullName: String
        get(): String { //커스텀 게터
            return "$firstName $familyName"
        }
        set(value) {
            val names = value.split(" ")
            if (names.size != 2) {
                throw IllegalArgumentException("Invalid full name: '$value")
            }
            firstName = names[0]
            familyName = names[1]
        }

    val fullName2: String //식이 본문인 형태를 사용할 수 있다.
        get() = "$firstName $familyName"

    val fullName3 //코틀린 1.1부터는 타입추론으로 인해 타입을 생략할 수 있다.
        get() = "$firstName $familyName"

    var age: Int? = null
        set(value) {
            if (value != null && value <= 0) {
                throw IllegalArgumentException("Invalid age: $value")
            }
            field = value
        }
}

val text by lazy { // lazy 프로퍼티는 thread-safe하다.
    File("data.txt").readText()
}

