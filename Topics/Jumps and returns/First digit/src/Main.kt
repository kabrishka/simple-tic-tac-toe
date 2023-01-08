fun main() {
    val str = readln()
    for(s in str) {
        if(s.digitToIntOrNull() == null) {
            continue
        }
        println(s)
        break
    }
}
