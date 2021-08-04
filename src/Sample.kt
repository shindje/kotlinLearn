import kotlinx.coroutines.GlobalScope   //Нужно подключить либу kotlinx-coroutines-core
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.Executors

fun main() {
    val pool = Executors.newFixedThreadPool(4)
    pool.submit({println("dfdf")})
    val pc = ProducerConsumer()
    GlobalScope.launch {
        while (true)
            pc.produce(2)

    }
    GlobalScope.launch {
        while (true)
            pc.produce(1)

    }
    GlobalScope.launch {
        while (true)
            pc.consume()
    }
}

class ProducerConsumer() {
    private val MAX_SIZE = 1000
    private val list = LinkedList<Int>()

    @Synchronized fun produce(n: Int){
            if (list.size < MAX_SIZE) {
                list.add(n*10_000 + (Math.random() * 1000).toInt())
                (this as Object).notify()
            } else
                (this as Object).wait()
    }

    @Synchronized fun consume() {
            if (list.size > 0) {
                println(list[0])
                list.remove()
                (this as Object).notify()
            } else
                (this as Object).wait()
    }
}