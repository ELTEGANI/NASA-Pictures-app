package com.example.nasapicturesapp.images

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasapicturesapp.R

class ImagesGridFragment : Fragment() {

    companion object {
        fun newInstance() = ImagesGridFragment()
    }

    private lateinit var viewModel: ImagesGridViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.images_grid_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImagesGridViewModel::class.java)
        // TODO: Use the ViewModel
    }

}