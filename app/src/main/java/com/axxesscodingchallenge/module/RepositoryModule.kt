package com.axxesscodingchallenge.module

import com.axxesscodingchallenge.ui.search.SearchRepository
import com.axxesscodingchallenge.ui.search.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    // Tells Koin how to create an instance of repository
    factory<SearchRepository> { SearchRepositoryImpl(get()) }

}