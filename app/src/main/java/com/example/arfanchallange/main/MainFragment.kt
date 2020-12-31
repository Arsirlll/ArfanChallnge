package com.example.arfanchallange.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.arfanchallange.databinding.FragmentMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            vm = (activity as MainActivity).obtainViewModel()

            action = object : MainActionListener {
                override fun onClickRepos() {
                    vm?.openRepo()
                }

            }
        }

        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewBinding.vm?.start()
    }

    companion object {
        fun newInstance() = MainFragment().apply {

        }
    }
}