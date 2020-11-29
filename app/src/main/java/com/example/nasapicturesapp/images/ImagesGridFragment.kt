package com.example.nasapicturesapp.images

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

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
        setHasOptionsMenu(true)
        return imagesGridFragmentBinding.root
    }
    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.action_night_mode -> {
                    item.isChecked = !item.isChecked
                    setUIMode(item, item.isChecked)
                    true
                }
                else -> false
            }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        // Set the item state
        lifecycleScope.launch {
            val isChecked = imagesGridViewModel.readDataStore.first()
            val item = menu.findItem(R.id.action_night_mode)
            item.isChecked = isChecked
            setUIMode(item, isChecked)
        }
    }
    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            imagesGridViewModel.saveToDataStore(true)
            item.setIcon(R.drawable.ic_night)

        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            imagesGridViewModel.saveToDataStore(false)
            item.setIcon(R.drawable.ic_day)

        }
    }
}