package com.example.nasapicturesapp.imagesdetailes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.databinding.ImageDetailScreenFragmentBinding
import com.example.nasapicturesapp.databinding.ImagesGridFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageDetailScreenFragment : Fragment() {

    private lateinit var imageDetailScreenFragmentBinding: ImageDetailScreenFragmentBinding
    private val imageDetailScreenViewModel:ImageDetailScreenViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        imageDetailScreenFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.image_detail_screen_fragment, container, false)

        val imageProperties = arguments?.let {
            ImageDetailScreenFragmentArgs.fromBundle(it).selectedProperties
        }

        Toast.makeText(context,imageProperties.toString(), Toast.LENGTH_SHORT).show()

        imageDetailScreenFragmentBinding.lifecycleOwner = this
        imageDetailScreenFragmentBinding.imageDetailesScreen = imageDetailScreenViewModel

        return imageDetailScreenFragmentBinding.root

    }
}