package com.kodebonek.qurannotes

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.kodebonek.qurannotes.data.db.entity.User
import com.kodebonek.qurannotes.ui.main.MainViewModel
import com.kodebonek.qurannotes.util.LiveDataTestUtil.getValue
import com.kodebonek.qurannotes.util.TestUtil
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.`when`

/**
 * @author <@Po10cio> on 11/3/17 for AndroidKotlinBoilerplate
 *
 */
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var repository: Repository

    @Captor private lateinit var userArgumentCaptor: ArgumentCaptor<User>

    private lateinit var mainViewModel: MainViewModel


    /**
     * Setup Mockito and other stuffs
     * */
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mainViewModel = MainViewModel(repository)
    }

    /**
     * Test load users when no user is saved
     * */
    @Test
    fun `loadUsers_whenNoUserSaved`() {
        `when`(repository.loadUsers()).thenReturn(TestUtil.createEmptyUsers())

        val users = getValue(mainViewModel.loadUsers())
        assertThat(users, nullValue())
    }

    /**
     * Test load user when a user is saved
     * */
    @Test
    fun `loadUser_whenUserSaved`() {
        `when`(repository.loadUsers()).thenReturn(TestUtil.createUsers())

        val users = getValue(mainViewModel.loadUsers())
        assertThat(users, notNullValue())

    }
}