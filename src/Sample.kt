import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.LinkedBlockingQueue

fun main() {
    val pc = ProducerConsumer()
    GlobalScope.launch {
        while (true)
            pc.produce(2)

    }
    GlobalScope.launch {
        while (true)
            pc.produce(1)

    }
    runBlocking {
        delay(1000)
    }
    runBlocking {
        pc.consume()
    }
}

class ProducerConsumer() {
    private val list = LinkedBlockingQueue <Int>()

    fun produce(n: Int) =
        list.add(n*10_000 + (Math.random() * 1000).toInt())

    fun consume() {
        var value =  list.poll()
        while (value != null) {
            println(value)
            value =  list.poll()
        }
    }
}