package com.example.news_mvvm.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class NewsApiServiceTest {

    private lateinit var service: NewsApiService
    private lateinit var server :MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url("https://newsapi.org"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }


    private fun enqueueMockResponse(fileName : String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()

        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }



//    @Test
//    fun getTopHeadlines_sendRequest_receivedExpected(){
//        runBlocking {
//            enqueueMockResponse("newsresources.json")
//            val responseBody = service.getTopHeadlines("us",1).body()
//            val request = server.takeRequest()
//
//            assertThat(responseBody).isNotNull()
//            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=40b0ad630c754940a449caaee98240a7")
//        }
//    }

    @Test
    fun getTopHeadline_receivedResponse_correctPageSize(){

        runBlocking {
            enqueueMockResponse("newsresources.json")
            val responseBody = service.getTopHeadlines("us",1).body()

            val articleList = responseBody!!.articles

            assertThat(articleList.size).isEqualTo(20)

        }
    }


  @Test
    fun getTopHeadline_receivedResponse_correctContent(){

        runBlocking {
            enqueueMockResponse("newsresources.json")
            val responseBody = service.getTopHeadlines("us",1).body()

            val articleList = responseBody!!.articles
            val  article = articleList[0]

            assertThat(article.title).isEqualTo("UAW-Ford deal nets union big wins on wages, benefits, investments - Reuters")
            assertThat(article.url).isEqualTo("https://www.reuters.com/business/autos-transportation/uaw-leaders-push-ahead-with-ford-contract-gm-talks-drag-2023-10-29/")

        }
    }





    @After
    fun tearDown() {
        server.shutdown()
    }


}