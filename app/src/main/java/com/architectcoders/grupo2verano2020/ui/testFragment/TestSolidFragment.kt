package com.architectcoders.grupo2verano2020.ui.testFragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.data.TestQuestionRepository
import com.architectcoders.grupo2verano2020.data.model.TestQuestion
import com.architectcoders.grupo2verano2020.databinding.FragmentTestSolidBinding
import com.architectcoders.grupo2verano2020.ui.common.app
import com.architectcoders.grupo2verano2020.ui.common.getViewModelF


class TestSolidFragment : Fragment(), TestSolidAdapter.Interaction {

    private lateinit var binding: FragmentTestSolidBinding


    lateinit var navController: NavController
    private lateinit var viewModel: TestSolidViewModel

    private lateinit var adapterQuestion: TestSolidAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestSolidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModelF { TestSolidViewModel(TestQuestionRepository(app)) }
        navController = view.findNavController()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(app)
            adapterQuestion = TestSolidAdapter(this@TestSolidFragment).apply {
                setHasStableIds(true)
            }
            adapter = adapterQuestion
        }

        val answers = resources.getStringArray(R.array.Answers)

        viewModel.questions.observe(viewLifecycleOwner, Observer(::updateUi))
        viewModel.getQuestionFromDb()

        binding.bntCheck.setOnClickListener {
            val answerSummary = viewModel.calculateResult()

            val result = compareValues(answers.toList(), answerSummary)

            val action=TestSolidFragmentDirections.actionTestFragmentToFirthFragment(result)
            navController.navigate(action)

        }
    }

    private fun updateUi(list: List<TestQuestion>?) {

        adapterQuestion.questions = list!!
    }

    private fun compareValues(answers: List<String>, userAnswer: List<String>): Int {
        var c = 0
        answers.forEachIndexed { idx, value ->
            if (userAnswer[idx] == value) {
                ++c
            }
        }
        val result = 100 * c / answers.size

        Log.d("TAG", "compareValues: thomy:: $result% ")
        return result
    }


    override fun onItemSelected(position: Int, selection: String) {

        viewModel.updateAnswer(position, selection)
        adapterQuestion.notifyDataSetChanged()
    }


}
