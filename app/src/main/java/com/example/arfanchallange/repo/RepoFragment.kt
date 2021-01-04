package com.example.arfanchallange.repo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.arfanchallange.R
import com.example.arfanchallange.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {

    private lateinit var viewBinding: FragmentRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentRepoBinding.inflate(inflater, container, false).apply {
            vm = (activity as RepoActivity).obtainViewModel()
        }
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRepo()
    }

    private fun setupRepo() {
        val viewModel = viewBinding.vm
        if (viewModel != null) {
            repoAdapter = RepoAdapter(viewModel.repoList, viewModel)
            viewBinding.rvRepo.adapter = repoAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    companion object {

        fun newInstance() = RepoFragment().apply {

        }
    }
}
