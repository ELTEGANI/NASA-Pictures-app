package com.example.nasapicturesapp.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.adapters.PhotoGridAdapter
import com.example.nasapicturesapp.databinding.ImagesGridFragmentBinding
import com.example.nasapicturesapp.imagesdetailes.ImageDetailScreenFragmentArgs
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.grid_view_item.*
import kotlinx.android.synthetic.main.images_grid_fragment.*

@AndroidEntryPoint
class ImagesGridFragment : Fragment() {

    private lateinit var imagesGridFragmentBinding: ImagesGridFragmentBinding
    private val imagesGridViewModel: ImagesGridViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        imagesGridFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.images_grid_fragment, container, false)

        imagesGridFragmentBinding.lifecycleOwner = this
        imagesGridFragmentBinding.imageGridViewModel = imagesGridViewModel

        exitTransition = MaterialElevationScale(/* growing= */ false)
        reenterTransition = MaterialElevationScale(/* growing= */ true)

        imagesGridFragmentBinding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            imagesGridViewModel.displayPropertyDetails(it)
        })

        imagesGridViewModel.navigateToSelectedProperty.observe(viewLifecycleOwner,{
        if ( null != it ) {
          val extras = FragmentNavigatorExtras((cardView to "shared_element_container") as Pair<View, String>)
          this.findNavController().navigate(ImagesGridFragmentDirections.actionImagesGridFragmentToImageDetailScreenFragment(it),extras)
           imagesGridViewModel.displayPropertyDetailsComplete()
         }
        })

        return imagesGridFragmentBinding.root
    }
}