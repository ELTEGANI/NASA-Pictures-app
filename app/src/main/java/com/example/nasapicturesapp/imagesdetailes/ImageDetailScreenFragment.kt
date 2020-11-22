package com.example.nasapicturesapp.imagesdetailes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasapicturesapp.R

class ImageDetailScreenFragment : Fragment() {

    companion object {
        fun newInstance() = ImageDetailScreenFragment()
    }

    private lateinit var viewModel: ImageDetailScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_detail_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageDetailScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}