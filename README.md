🚀 Kotlin Coroutines & Suspend Functions - Explained
📌 Overview
This project demonstrates how to use Kotlin Coroutines in an Android application to handle background tasks efficiently. It covers:
✅ Coroutine Scopes (viewModelScope, lifecycleScope, GlobalScope)
✅ Coroutine Builders (launch, async)
✅ Suspending Functions (suspend keyword)
✅ Coroutine Dispatchers (Dispatchers.Main, Dispatchers.IO, Dispatchers.Default, Dispatchers.Unconfined)
✅ Exception Handling in Coroutines
✅ Kotlin Flow for Real-Time Updates

🔹 Coroutine Scopes
Types of Coroutine Scopes:
viewModelScope – Best for UI-related tasks inside a ViewModel (Auto-cancels on ViewModel clear).
lifecycleScope – Best for activities/fragments (Cancels when lifecycle reaches onDestroy).
GlobalScope – Used for long-running tasks (Not recommended for UI, as it runs for app lifetime).

🔹 Coroutine Builders: launch vs async
launch { } → Fire-and-forget, does not return a result (Used for background tasks).
async { } → Returns Deferred<T>, which allows fetching a result asynchronously.

🔹 Suspend Functions & suspend Keyword
suspend is a modifier that marks a function as a suspending function.
Suspending functions can be paused and resumed without blocking threads.
They must be called inside a coroutine or another suspending function.

🔹 Coroutine Dispatchers
Types of Dispatchers:
Dispatchers.Main → Runs on the Main Thread (UI-related work)
Dispatchers.IO → Used for network/database operations (Background tasks)
Dispatchers.Default → Used for CPU-intensive tasks (Heavy computations)
Dispatchers.Unconfined → Starts on the caller thread, but may continue in a different thread

