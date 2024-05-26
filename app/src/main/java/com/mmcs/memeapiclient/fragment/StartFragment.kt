package com.mmcs.memeapiclient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mmcs.memeapiclient.R
import com.mmcs.memeapiclient.apimodel.MemesPostBody
import com.mmcs.memeapiclient.database.GlobalDbApplication
import com.mmcs.memeapiclient.database.ViewModelFactory
import com.mmcs.memeapiclient.viewmodel.MemeApiClientViewModel
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dbApp: GlobalDbApplication
    private val sharedData: MemeApiClientViewModel by activityViewModels {
        ViewModelFactory(dbApp.getDB().dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dbApp = context?.applicationContext as GlobalDbApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()
        setImageByInd(sharedData.adapterPosition, view)

        view.findViewById<Button>(R.id.NewImageButton).setOnClickListener {
            sharedData.getRandomImageFromApi {imageModel ->
                sharedData.addImage(imageModel)
                sharedData.adapterPosition = sharedData.getImageCount() - 1
                setImageByInd(sharedData.adapterPosition, view)
            }
        }
        view.findViewById<Button>(R.id.AddRemoveTextButton).setOnClickListener {
            if (sharedData.adapterPosition < 0 ||
                sharedData.adapterPosition >= sharedData.getImageCount())
                return@setOnClickListener

            val imageData = sharedData.getImage(sharedData.adapterPosition)
            val boxes = mutableListOf<String>()

            for (i in 1..imageData.box_count) {
                val captionInput = view.findViewWithTag<TextInputLayout>("caption $i")
                val text = captionInput.editText?.text.toString()
                if (text.isEmpty() && i != 1) {
                    if (imageData.og_url != "")
                        imageData.url = imageData.og_url
                    Picasso.get()
                        .load(imageData.url)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(view.findViewById<ImageView>(R.id.imageView))
                    sharedData.updateImage(imageData)
                    return@setOnClickListener
                }
                boxes.add(text)
            }

            val postRequest = MemesPostBody(imageData.id, boxes = boxes)
            sharedData.postAddCaptions(postRequest) {
                if (imageData.og_url == "")
                    imageData.og_url = imageData.url
                imageData.url = it.data.url
                sharedData.updateImage(imageData)
                Picasso.get()
                    .load(imageData.url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(view.findViewById<ImageView>(R.id.imageView))
            }
        }

        /*view.findViewById<Button>(R.id.DownloadButton).setOnClickListener {
            val imageData = sharedData.getImage(sharedData.adapterPosition)
            val url = URL(imageData.url)
            val input: InputStream = url.openStream()
            try {
                val storagePath = Environment.getExternalStorageDirectory()
                val output: OutputStream = FileOutputStream("$storagePath/${imageData.name}.png")
                try {
                    val buffer = ByteArray(1024)
                    var bytesRead = 0
                    while (input.read(buffer, 0, buffer.size).also { bytesRead = it } >= 0) {
                        output.write(buffer, 0, bytesRead)
                    }
                } finally {
                    output.close()
                }
            } finally {
                input.close()
            }
        }*/

        view.findViewById<Button>(R.id.HistoryButton).setOnClickListener {
            navController.navigate(R.id.historyFragment)
        }
    }

    override fun onStart() {
        super.onStart()

        view?.let { setImageByInd(sharedData.adapterPosition, it) }
        activity?.setTitle("Meme generator")

    }

    private fun setImageByInd(ind: Int, view: View){
        val imgView = view.findViewById<ImageView>(R.id.imageView)
        if (ind < 0 || ind > sharedData.getImageCount()) {
            val nullPath: String? = null
            Picasso.get().load(nullPath).placeholder(R.drawable.ic_launcher_foreground).into(imgView)
            return
        }
        val imageData = sharedData.getImage(ind)
        Picasso.get().load(imageData.url).into(imgView)
        val captionsLayout = view.findViewById<LinearLayout>(R.id.captionsLinearLayout)
        captionsLayout.removeAllViews()


        for (i in 1..imageData.box_count) {
            val textInput = LayoutInflater.from(context).inflate(R.layout.text_input_item, captionsLayout, false)
            textInput.tag = "caption $i"
            textInput.findViewById<TextInputEditText>(R.id.editText).hint = "Caption $i"
            captionsLayout.addView(textInput)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
