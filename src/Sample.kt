import java.util.*
import java.util.concurrent.Executors

fun main() {
    val pool = Executors.newFixedThreadPool(4)
    pool.submit({println("dfdf")})
    val pc = ProducerConsumer()
    val t0 = Thread() {
        while (true)
            pc.produce(2)
    }
    val t1 = Thread() {
        while (true)
            pc.produce(1)
    }
    val t2 = Thread() {
        while (true)
            pc.consume()
    }
    t0.start()
    t1.start()
    t2.start()
    t0.join()
    t1.join()
    t2.join()
}

//Java like
class ProducerConsumer() {
    private val MAX_SIZE = 1000
    private val list = LinkedList<Int>()

    @Synchronized fun produce(n: Int){
        //synchronized(this) {          // oldstyle
            if (list.size < MAX_SIZE) {
                list.add(n*10_000 + (Math.random() * 1000).toInt())
                (this as Object).notify()
            } else
                (this as Object).wait()
        //}
    }

    @Synchronized fun consume() {
        //synchronized(this) {
            if (list.size > 0) {
                println(list[0])
                list.remove()
                (this as Object).notify()
            } else
                (this as Object).wait()
        //}
    }
}