package com.example.nasapicturesapp.imagesdetailes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.example.nasapicturesapp.R
import com.example.nasapicturesapp.databinding.ImageDetailScreenFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ImageDetailScreenFragment : Fragment() {

    private lateinit var imageDetailScreenFragmentBinding: ImageDetailScreenFragmentBinding
    private val imageDetailScreenViewModel:ImageDetailScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        imageDetailScreenFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.image_detail_screen_fragment,
            container,
            false
        )

        imageDetailScreenFragmentBinding.lifecycleOwner = this
        imageDetailScreenFragmentBinding.imageDetailesScreen = imageDetailScreenViewModel

        val imageProperties = arguments?.let {
            ImageDetailScreenFragmentArgs.fromBundle(it).selectedProperties
        }

        lifecycle.coroutineScope.launch {
            val images = HashMap<String, String>()
            images[imageProperties?.title.toString() + '\n' + imageProperties?.date.toString()
            + '\n' + imageProperties?.explanation.toString()] = imageProperties?.url.toString()
            images[""] = imageProperties?.hdurl.toString()
            Log.d("images",images.toString())
            for (name in images.keys) {
                val textSliderView = TextSliderView(context)
                textSliderView.description(name)
                textSliderView.image(images[name]).scaleType = BaseSliderView.ScaleType.Fit
                imageDetailScreenFragmentBinding.slider.addSlider(textSliderView)
            }
            imageDetailScreenFragmentBinding.slider.setPresetTransformer(SliderLayout.Transformer.Accordion)
            imageDetailScreenFragmentBinding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
            imageDetailScreenFragmentBinding.slider.setCustomAnimation(DescriptionAnimation())
        }

        return imageDetailScreenFragmentBinding.root

    }

}