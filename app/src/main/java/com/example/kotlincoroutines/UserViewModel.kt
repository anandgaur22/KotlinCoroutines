package com.example.kotlincoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserViewModel : ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    private val _postsState = MutableStateFlow<List<Post>>(emptyList())
    val postsState: StateFlow<List<Post>> = _postsState

    private val apiService = RetrofitClient.apiService

    fun fetchUserData() {
        viewModelScope.launch {
            try {
                // Use async with IO dispatcher to fetch data in parallel
                val userDeferred = async(Dispatchers.IO) { apiService.getUser(1) }
                val postsDeferred = async(Dispatchers.IO) { apiService.getPostsByUserId(1) }

                // Await the results
                val userResponse = userDeferred.await()
                val postsResponse = postsDeferred.await()

                // Switch back to Main thread to update UI
                withContext(Dispatchers.Main) {
                    _userState.value = userResponse
                    _postsState.value = postsResponse
                }
            } catch (e: IOException) {
                // Handle network failure (e.g., show an error message)
            } catch (e: HttpException) {
                // Handle HTTP error response (e.g., show an error message)
            }

        }
    }
}
