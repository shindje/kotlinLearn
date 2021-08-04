import java.util.*
import java.util.concurrent.*

fun main() {
    val syncList = ConcurrentLinkedQueue<Int>() //LinkedBlockingQueue - better to block avoiding OutOfMemory
    for (i in 1..1000)
        syncList.add(i)
    val t1 = Thread {
        for (s in syncList)
            println(s)
    }
    val t2 = Thread {
        for (i in 1..1000)
            syncList.add(i + 1000)
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