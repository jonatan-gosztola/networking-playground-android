package hu.csoniworks.networkingplayground.di

import hu.csoniworks.networkingplayground.data.DataSource
import hu.csoniworks.networkingplayground.data.DataSourceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkingModule = module {

    single<HttpClient> {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<DataSource> { DataSourceImpl(get()) }

}