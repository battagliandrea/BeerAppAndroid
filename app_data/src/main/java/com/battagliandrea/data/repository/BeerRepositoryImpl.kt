package com.battagliandrea.data.repository

import com.battagliandrea.data.datasource.PunkApiDataSource
import com.battagliandrea.domain.entity.BeerEntity
import com.battagliandrea.domain.repository.BeerRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class BeerRepositoryImpl @Inject constructor(
        private val punkApiDataSource: PunkApiDataSource
) : BeerRepository {

    private val LIMIT_SIZE = 25

    private var currentPage: Int = 1
    private var isLastPage: Boolean = false
    private val beersCache: MutableList<BeerEntity> = mutableListOf()

    @ExperimentalCoroutinesApi
    private val beersChannel: ConflatedBroadcastChannel<Pair<List<BeerEntity>, Boolean>> = ConflatedBroadcastChannel()

    @FlowPreview
    @ExperimentalCoroutinesApi
    override suspend fun observe(): Flow<Pair<List<BeerEntity>, Boolean>> {
        return beersChannel.asFlow()
    }

    @ExperimentalCoroutinesApi
    override suspend fun sync(refresh: Boolean) {

        if(refresh){
            currentPage = 1
            beersCache.clear()
        }

        return punkApiDataSource.getBeers(page = currentPage, limit = LIMIT_SIZE)
            .let { remoteData ->
                beersCache.addAll(remoteData)

                currentPage += 1
                isLastPage = remoteData.size < LIMIT_SIZE

                beersChannel.send(beersCache to isLastPage)
            }
    }

    @ExperimentalCoroutinesApi
    override suspend fun filter(text: String) {
        if(text.length >= 3){
            delay(500)

            punkApiDataSource.filterBeers(beerName = text)
                .let { remoteData ->
                    beersChannel.send(remoteData to true)
                }
        } else {
            beersChannel.send(beersCache to isLastPage)
        }
    }

    override suspend fun get(id: Long): BeerEntity {
        return punkApiDataSource.getBeer(id)
    }
}
