package com.islam.shutterstock.data.network.dataSource

import androidx.paging.PagingSource
import com.islam.shutterstock.data.repositories.FakeSearchImageRepositoryTest
import com.islam.shutterstock.data.repositories.ImageFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class HomeDataSourceTest {

    private val imageFactory = ImageFactory()

    private val fakeImages = listOf(
        imageFactory.createImageItem(),
        imageFactory.createImageItem(),
        imageFactory.createImageItem(),
        imageFactory.createImageItem(),
        imageFactory.createImageItem()
    )

    @Test
    fun `load Returns Page When OnSuccessfulLoad Of Page Keyed Data`() = runBlockingTest {
        val pagingSource = HomeDataSource(FakeSearchImageRepositoryTest(fakeImages, false))

        assertEquals(
            expected = PagingSource.LoadResult.Page(
                data = fakeImages,
                prevKey = null,
                nextKey = fakeImages[1].id.toInt()
            ),
            actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = fakeImages.size,
                    placeholdersEnabled = false
                )
            )
        )
    }

}