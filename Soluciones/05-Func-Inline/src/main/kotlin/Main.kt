package dev.joseluisgs

fun myRepeat(times: Int, action: (Int) -> Unit) {
    for (i in 0..<times) {
        action(i)
    }
}

inline fun myRepeatInline(times: Int, action: (Int) -> Unit) {
    for (i in 0..<times) {
        action(i)
    }
}

fun main() {
    myRepeat(10) { println("Hello Repeat $it") }
    myRepeatInline(10) { println("Hello Repeat Inline $it") }
    myRepeat(10) { println("Hello Repeat $it") }
    myRepeatInline(10) { println("Hello Repeat Inline $it") }
}