package com.example.kotlincoroutines


/*

----Traditional Thread Approach-----

fun fetchData() {
    Thread {
        Thread.sleep(5000) // Thread will be blocked for 5 seconds
        println("Data fetched!")
    }.start()
}

----Coroutine Approach-----

fun main() {
    GlobalScope.launch {
        delay(5000)  // Will suspend but not block thread
        println("Data fetched!")
    }
    Thread.sleep(6000) // Will have to wait for Coroutine Executive
}

------Coroutine Scopes-----

1. viewModelScope

class UserViewModel : ViewModel() {
    fun fetchUserData() {
        viewModelScope.launch {
            val userData = apiService.getUser(1)
            // Process user data
        }
    }
}

2. lifecycleScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            // Background task that should be cancelled when Activity is destroyed
            val data = apiService.getData()
            // Update UI with data
        }
    }
}

3. GlobalScope

GlobalScope.launch {
    // Long-running background task
    val result = apiService.getSomeData()
    // This won't be cancelled automatically
}


---launch example----

viewModelScope.launch {
    apiService.getUser(1) // Fetch user but no return needed
    println("User fetched successfully")
}


--async example----

viewModelScope.launch {
    val userDeferred = async { apiService.getUser(1) }
    val postsDeferred = async { apiService.getPostsByUserId(1) }

    val user = userDeferred.await() // Waiting for user data
    val posts = postsDeferred.await() // Waiting for posts data

    println("User: ${user.name}, Posts: ${posts.size}")
}

Deferred<T>




 1. Dispatchers.Main


fun main() = runBlocking {
    launch(Dispatchers.Main) { // ❌ (Will crash in a non-Android environment)
        println("Running on UI Thread")
    }
}

2. Dispatchers.IO

fun main() = runBlocking {
    launch(Dispatchers.IO) {
        println("Fetching data from server...")
        delay(2000) // Simulating network delay
        println("Data fetched!")
    }
}

3. Dispatchers.Default

fun main() = runBlocking {
    launch(Dispatchers.Default) {
        println("Starting heavy computation...")
        val result = heavyCalculation()
        println("Computation Result: $result")
    }
}

fun heavyCalculation(): Int {
    var sum = 0
    for (i in 1..1_000_000) { sum += i }
    return sum
}

4. Dispatchers.Unconfined

fun main() = runBlocking {
    launch(Dispatchers.Unconfined) {
        println("Started on thread: ${Thread.currentThread().name}")
        delay(1000) // Thread switch may occur after delay
        println("Resumed on thread: ${Thread.currentThread().name}")
    }
}

fun main() = runBlocking
{

fetchData()

}


suspend fun fetchData()
{

delay(2000)
println("Data fetch")

}

suspend fun myFunction()

{

println(""Hello")

delay(2000)

println("world")

}



* */
