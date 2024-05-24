package com.mmcs.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.mmcs.todolist.database.GlobalDBApplication
import com.mmcs.todolist.database.TaskModel
import com.mmcs.todolist.database.ViewModelFactory
import com.mmcs.todolist.viewmodel.ToDoListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewNodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewNodeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dbApp: GlobalDBApplication
    private val sharedData: ToDoListViewModel by activityViewModels {
        ViewModelFactory(dbApp.getDB().dao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dbApp = context?.applicationContext as GlobalDBApplication
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_node, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()

        view.findViewById<Button>(R.id.OKAddButton).setOnClickListener {
            val name = view.findViewById<TextInputLayout>(R.id.NameInputField).editText?.text.toString()
            val description = view.findViewById<TextInputLayout>(R.id.DescriptionInputField).editText?.text.toString()
            sharedData.addTask(TaskModel(name, description, false))
            navController.popBackStack()
        }

        view.findViewById<Button>(R.id.CancelAddButton).setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.setTitle("New task")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewNodeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewNodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}