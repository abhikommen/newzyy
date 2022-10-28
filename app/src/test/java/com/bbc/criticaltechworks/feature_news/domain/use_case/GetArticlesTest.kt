package com.bbc.criticaltechworks.feature_news.domain.use_case

import com.bbc.criticaltechworks.common.utils.DataState
import com.bbc.criticaltechworks.feature_news.data.repository.FakeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetArticlesTest {

    private lateinit var getArticles: GetArticles
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setUp() {
        fakeRepository = FakeRepository()
        getArticles = GetArticles(fakeRepository)
    }

    @Test
    fun `Headlines must be sorted by date, testing`() = runBlocking {
        getArticles().collect {
            when (it) {
                is DataState.Error -> {

                }
                DataState.Loading -> {
                    println("⭕️ articles are loading")
                    delay(500) // mocking network request-response time..
                }
                is DataState.Success -> {
                    println("📰️ articles returned from server..")
                    for (i in 0..it.data.size - 2) {
                        assert(it.data[i].publishedAt.time > it.data[i + 1].publishedAt.time) {
                            "⚠️ Headlines must be sorted by date. Which mean newer post must come " +
                                    "first on top of the list"
                        }
                    }
                    println("✅ Articles are perfectly sorted ")
                }
            }
        }
    }

    @Test
    fun `Offline Support Articles, testing`() = runBlocking {
        `Headlines must be sorted by date, testing`()
        println("forcefully triggering api error 📶 ..")
        fakeRepository.internetAvailable = false // forcefully triggering api error..
        getArticles().collect {
            when (it) {
                is DataState.Error -> {
                    assert(true) {
                        "⚠️ ️Clearly we have local data stubbed in FakeRepository, how can it go into DataState.Error ??? "
                    }
                }
                DataState.Loading -> {
                    println("⭕️ mocking articles to load from web first, then from local cache")
                }
                is DataState.Success -> {
                    println("📰️ articles returned from local database..")
                    for (i in 0..it.data.size - 2) {
                        assert(it.data[i].publishedAt.time > it.data[i + 1].publishedAt.time) {
                            "Headlines must be sorted by date. Which mean newer post must come " +
                                    "first on top of the list"
                        }
                    }
                    println("✅ Articles are perfectly sorted ")
                }
            }
        }
    }


}
