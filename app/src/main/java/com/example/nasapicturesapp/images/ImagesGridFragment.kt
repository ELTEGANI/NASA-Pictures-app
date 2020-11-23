package com.example.nasapicturesapp.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.adapters.PhotoGridAdapter
import com.example.nasapicturesapp.databinding.ImagesGridFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesGridFragment : Fragment() {

    private lateinit var imagesGridFragmentBinding: ImagesGridFragmentBinding
    private val imagesGridViewModel: ImagesGridViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        imagesGridFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.images_grid_fragment, container, false)

        imagesGridFragmentBinding.lifecycleOwner = this
        imagesGridFragmentBinding.imageGridViewModel = imagesGridViewModel

        imagesGridFragmentBinding.photosGrid.adapter = PhotoGridAdapter()

        return imagesGridFragmentBinding.root
    }
}