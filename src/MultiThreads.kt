import java.util.*

fun main() {
    val syncList = Collections.synchronizedList(MutableList(1000, {i -> i}))
    val t1 = Thread {
        for (s in syncList)
            println(s)
    }
    val t2 = Thread {
        for (i in 1..1000)
            syncList.add(i)
    }
    t1.start()
    t2.start()
    t1.join()
    t2.join()
//    for (s in syncList)
//        println(s)
}

class MultiThreads {

}