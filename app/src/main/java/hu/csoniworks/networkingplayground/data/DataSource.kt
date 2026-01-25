package hu.csoniworks.networkingplayground.data

import hu.csoniworks.networkingplayground.data.models.Comment
import hu.csoniworks.networkingplayground.data.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DataSource {

    suspend fun fetchPosts()

    suspend fun fetchComments()

    fun hasError(): StateFlow<Boolean>

    fun getPosts(): Flow<List<Post>>

    fun getComments(postId: Int): Flow<List<Comment>>
}