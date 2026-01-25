package hu.csoniworks.networkingplayground.data

import android.util.Log
import hu.csoniworks.networkingplayground.data.models.Comment
import hu.csoniworks.networkingplayground.data.models.Post
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataSourceImpl(
    private val client: HttpClient,
) : DataSource {

    private val hasError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val list: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    private val comments: MutableStateFlow<List<Comment>> = MutableStateFlow(emptyList())

    override fun getPosts(): Flow<List<Post>> = list.asStateFlow()

    override fun getComments(postId: Int): Flow<List<Comment>> =
        comments.map { commentList ->
            commentList.filter { it.postId == postId }
        }

    override fun hasError(): StateFlow<Boolean> = hasError.asStateFlow()

    override suspend fun fetchPosts() {
        withContext(Dispatchers.Default) {
            try {
                hasError.value = false
                val response = client.get("$baseUrl/posts").body<List<Post>>()
                list.value = response
            } catch (ex: Exception) {
                Log.e("DataSource", "Error during fetching posts", ex)
                hasError.value = true
            }
        }
    }

    override suspend fun fetchComments() {
        withContext(Dispatchers.Default) {
            try {
                hasError.value = false
                val response = client.get("$baseUrl/comments").body<List<Comment>>()
                comments.value = response
            } catch (ex: Exception) {
                Log.e("DataSource", "Error during fetching comments", ex)
                hasError.value = true
            }
        }
    }


    companion object {
        private const val baseUrl = "https://jsonplaceholder.typicode.com"
    }
}
