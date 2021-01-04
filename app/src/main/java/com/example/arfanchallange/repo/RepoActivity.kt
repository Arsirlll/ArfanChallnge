package com.example.arfanchallange.repo

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.arfanchallange.R
import com.example.arfanchallange.util.obtainViewModel
import com.example.arfanchallange.util.replaceFragmentInActivity

class RepoActivity : AppCompatActivity() {

    private lateinit var mActivity: AppCompatActivity
    private lateinit var viewModel: RepoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repo_activity)

        mActivity = this
        setupViewModel()
        setupFragmnet()
    }

    private fun setupViewModel() {
        viewModel = obtainViewModel().apply {
            openRepo.observe(this@RepoActivity, Observer {
                onRepoClicked(it!!)
            })
        }
    }

    private fun setupFragmnet() {
        supportFragmentManager.findFragmentById(R.id.frameRepo)
        RepoFragment.newInstance().let {
            replaceFragmentInActivity(it, R.id.frameRepo)
        }
    }

    fun onRepoClicked(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(mActivity, R.color.design_default_color_primary))
        customTabsIntent.launchUrl(mActivity, Uri.parse(url))
    }

    fun obtainViewModel(): RepoViewModel = obtainViewModel(RepoViewModel::class.java)
}