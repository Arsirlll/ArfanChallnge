package com.example.arfanchallange.util

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.nio.file.Path

fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
    supportFragmentManager.transaction {
        replace(frameId, fragment)
    }
}

private inline fun FragmentManager.transact(action: androidx.fragment.app.FragmentTransaction. () -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun AppCompatActivity.AddFragmentToActivity(fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(fragment, tag)
    }
}

val Context.picasson: Picasso get() = Picasso.get()

fun ImageView.load(path: String, request: (RequestCreator) -> RequestCreator) {
    request(context.picasson.load(path)).into(this)
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        view.load(url) { requestCreator ->
            requestCreator.fit().centerCrop()
        }
    }
}