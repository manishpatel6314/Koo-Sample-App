package com.manish.koo.post

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.manish.koo.R
import com.manish.koo.databinding.PostFragmentBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.post_fragment.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PostFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PostViewModel by viewModels { viewModelFactory }

    private lateinit var binding : PostFragmentBinding

    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.post_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PostAdapter().also {
            recycler.adapter = it
        }

        setUpPagination()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun setUpPagination() {
        adapter.loadStateFlow
            .launchIn(lifecycleScope)

        viewModel.getPagerData().pagingData.onEach { adapter.submitData(it) }
            .launchIn(lifecycleScope)
    }

}